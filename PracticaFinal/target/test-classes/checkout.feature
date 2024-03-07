Feature: Verificar checkout

  Background:
    Given me encuentro en la pagina home

  Scenario Outline: Validar que el precio de un producto a comprar es correcto como usuario registrado
    When anado el producto de feature "<product>" al carrito "1" veces
    And hago click en el boton de checkout
    And introduzco el email "ivangomcav@gmail.com" y la contrasena "Boligrafo_12" en el checkout
    And introduzco los datos de facturacion: firstName "Ivan", lastName "Gomez", email "null", telephone "null", company "Hiberus", address1 "C. de los Vinos de la Ribera del Duero, 7", address2 "null", city "Valladolid", postCode "47008", country "Spain", region "Valladolid", radioButton "true"
    And hago click en el boton de continuar en el payment method aceptando los terminos y condiciones
    Then compruebo que el subtotal es el esperado

    Examples:
      | product |
      | MacBook |

  Scenario Outline: Realizar el checkout como usuario sin registrar
    When anado el producto de feature "<product>" al carrito "1" veces
    And hago click en el boton de checkout
    And le doy click a invitado y continuar
    And introduzco los datos de facturacion: firstName "Ivan", lastName "Gomez", email "ivangomcav@gmail.com", telephone "675643098", company "Hiberus", address1 "C. de los Vinos de la Ribera del Duero, 7", address2 "null", city "Valladolid", postCode "47008", country "Spain", region "Valladolid", radioButton "false"
    And hago click en el boton de continuar en el payment method aceptando los terminos y condiciones
    And confirmo el pedido
    Then aparece el mensaje que muestra la confirmacion de pedidio "Your order has been placed!"

    Examples:
      | product |
      | MacBook |


#  Scenario Outline: Validar que el pedido de un producto se añade al apartado order history