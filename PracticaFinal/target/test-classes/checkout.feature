Feature: Verificar checkout

  Background:
    Given me encuentro en la pagina home

  Scenario: Validar checkout con todos los datos correctos
    When anado el producto "MacBook" al carrito
    And hago click en el boton de checkout
    And introduzco el email "ivangomcav@gmail.com" y la contrasena "Boligrafo_12"
    And introduzco los datos de envio: firstName "Ivan", lastName "Gomez", company "Hiberus", address1 "C. de los Vinos de la Ribera del Duero, 7", city "Valladolid", postCode "47008", country "Spain", region "Valladolid"
    And hago click en el boton de continuar en el delivery details
    And hago click en el boton de continuar en el delivery method
    And acepto los terminos y condiciones y hago click en el boton continuar
    And compruebo que el subtotal es correcto
    And hago click en confirm order
    Then se muestra un mensaje de pedido realizado "Your order has been placed!"