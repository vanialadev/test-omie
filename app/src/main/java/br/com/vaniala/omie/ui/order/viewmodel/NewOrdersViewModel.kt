package br.com.vaniala.omie.ui.order.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.vaniala.omie.domain.model.ItemModel
import br.com.vaniala.omie.domain.model.OrderModel
import br.com.vaniala.omie.domain.usecase.item.GetAllItemsUseCase
import br.com.vaniala.omie.domain.usecase.login.GetIdUserCase
import br.com.vaniala.omie.domain.usecase.order.InsertOrdersUseCase
import br.com.vaniala.omie.extensions.isListValid
import br.com.vaniala.omie.extensions.isValidName
import br.com.vaniala.omie.utils.ValidationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 19/03/23.
 *
 */
@HiltViewModel
class NewOrdersViewModel @Inject constructor(
    private val getIdUserCase: GetIdUserCase,
    private val getAllItemsUseCase: GetAllItemsUseCase,
    private val insertOrdersUseCase: InsertOrdersUseCase,
) : ViewModel() {
    private val _orderItemsState = MutableStateFlow(
        OrderItems(
            emptyList(),
            0.0,
            0,
        ),
    )
    private val _itemAdapter = MutableStateFlow(
        ItemAdapter(1, 0.0),
    )

    val orderItemsState: Flow<OrderItems>
        get() = _orderItemsState

    val itemAdapter: Flow<ItemAdapter>
        get() = _itemAdapter

    private val _error = MutableSharedFlow<String>()
    val error: Flow<String> = _error

    private val _idUser: MutableStateFlow<Long?> = MutableStateFlow(null)
    val idUser: Flow<Long?> = _idUser

    private val _items = MutableStateFlow<List<ItemModel>>(emptyList())
    val items: Flow<List<ItemModel>> = _items

    private val _state = MutableStateFlow(ValidationState())
    val state: Flow<ValidationState> = _state

    private val _saveOrders = MutableSharedFlow<Unit>()
    val saveOrders: Flow<Unit> = _saveOrders

    suspend fun addItemToOrder(item: ItemModel) {
        val currentItems = _orderItemsState.value.items.toMutableList()
        val existingItem = currentItems.find { it.idItem == item.idItem }
        if (existingItem != null) {
            _error.emit("Produto já adicionado")
            return
        } else {
            currentItems.add(item)
        }
        val currentPriceTotal = _orderItemsState.value.priceTotal + item.price
        val currentTotalItems = _orderItemsState.value.totalItems + 1

        _orderItemsState.value = OrderItems(currentItems, currentPriceTotal, currentTotalItems)
        _itemAdapter.value = ItemAdapter(1, item.price)
    }

    private fun removeItemFromOrder(item: ItemModel) {
        val currentItems = _orderItemsState.value.items.toMutableList()
        currentItems.remove(item)

        val currentPriceTotal = _orderItemsState.value.priceTotal - item.price
        val currentTotalItems = _orderItemsState.value.totalItems
        if (_orderItemsState.value.totalItems > 0) {
            currentTotalItems - 1
        }
        _orderItemsState.value = OrderItems(currentItems, currentPriceTotal, currentTotalItems)
    }

    fun updateItemQuantity(item: ItemModel, quantity: Int) {
        if (item.quantity + quantity <= 0) {
            removeItemFromOrder(item)
        }
        val currentItems = _orderItemsState.value.items.toMutableList()
        var currentQuantity = item.quantity
        currentQuantity += quantity
        var existingItem = currentItems.find { it.idItem == item.idItem }
        existingItem?.quantity = currentQuantity

        val currentPriceTotalOrder = currentItems.sumOf { it.price * it.quantity }
        val currentPriceTotal = item.price * item.quantity

        val currentTotalItems = _orderItemsState.value.totalItems + quantity

        _orderItemsState.value =
            OrderItems(currentItems, currentPriceTotalOrder, currentTotalItems)
        _itemAdapter.value = ItemAdapter(currentQuantity, currentPriceTotal)
    }

    fun saveOrder(name: String) {
        if (validateInputs(name)) {
            val orderItems = OrderItems(
                _orderItemsState.value.items,
                _orderItemsState.value.priceTotal,
                _orderItemsState.value.totalItems,
                name,
            )

            val orderModel = OrderModel(
                idUser = _idUser.value ?: 0,
                totalPrice = orderItems.priceTotal,
                name = name,
            )

            viewModelScope.launch {
                when (insertOrdersUseCase(orderModel, orderItems.items)) {
                    InsertOrdersUseCase.Result.Success -> {
                        _saveOrders.emit(Unit)
                    }
                    InsertOrdersUseCase.Result.Failure ->
                        _error.emit("Erro ao salvar pedido")
                }
            }
        }
        val a = _orderItemsState.value
        Timber.d("ORDER: $a")
    }

    init {
        viewModelScope.launch {
            _idUser.value = getIdUserCase().firstOrNull()
            Timber.d("ID USER: $idUser")

            getAllItemsUseCase().collect { items ->
                _items.emit(items)
            }
        }
    }

    private fun validateInputs(name: String): Boolean {
        val isNameValid = name.isValidName()
        val isListValid = _orderItemsState.value.items.isListValid()

        _state.value =
            _state.value.copy(isNameValid = isNameValid, isListValid = isListValid)

        return isNameValid && isListValid
    }
}

data class OrderItems(
    val items: List<ItemModel>,
    val priceTotal: Double,
    val totalItems: Int,
    val client: String? = null,
)

data class ItemAdapter(
    val quantity: Int,
    val priceTotal: Double,
)
