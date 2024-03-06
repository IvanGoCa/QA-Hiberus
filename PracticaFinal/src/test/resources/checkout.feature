Feature: Verificar checkout

  Background:
    Given me encuentro en la pagina home

  Scenario: Validar que el precio de un producto a comprar es correcto como usuario registrado
    When anado el producto de feature "<product>" al carrito "1" veces
    And hago click en el boton de checkout
    And introduzco el email "ivangomcav@gmail.com" y la contrasena "Boligrafo_12" en el checkout
    And introduzco los datos de facturacion: firstName "Ivan", lastName "Gomez", company "Hiberus", address1 "C. de los Vinos de la Ribera del Duero, 7", city "Valladolid", postCode "47008", country "Spain", region "Valladolid"
    And hago click en el boton de continuar en el payment method aceptando los terminos y condiciones
    Then compruebo que el subtotal es el esperado

    Examples:
    | product |
    | MacBook |

    # And hago click en el boton de continuar en el delivery details
    # And hago click en el boton de continuar en el delivery method
    # And hago click en confirm order
    # Then se muestra un mensaje de pedido realizado "Your order has been placed!"