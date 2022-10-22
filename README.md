# Copanga House

> ### Um app simples para controle de uma casa automatizada com Arduino/ESP32 🤖

---

## Como funciona? 🤔

### O aplicativo ele é bem simples, possui somente uma tela principal com os botões para ligar e desligar os comodos de uma casa.

### Ele funciona a partir do protocolo Bluetooth Low Energy (BLE) que é um protocolo de baixo consumo de energia, que permite a comunicação entre dispositivos sem a necessidade de uma conexão física entre eles.

<br>

## Parte fisica 🛠

### A parte física do projeto é bem simples, basta ter um Arduino/ESP32 e um módulo Bluetooth Low Energy (BLE) para que o projeto funcione.

<p align="center">
  <img alt="Casa" width="500" src="https://user-images.githubusercontent.com/97262778/195239273-bf10a403-5798-4424-b35a-58e5066edd52.jpeg"/>
  <!-- Imagem do circuito aqui -->
</p>

<br>

## O aplicativo 📱

### O aplicativo foi desenvolvio em Java no Android Studio. Ele é bem simples, possui um botão para conectar e selecionar o dispositivo Bluetooth e quando conectado ele altera para um botão para desconectar. Os outros botões são para ligar e desligar os comodos da casa.

<p align="center">
  <!-- Colocar logo aqui -->
</p>

<br>

## Manual de funcionamento 📖

### Antes de abrir o aplicativo, é necessário que o Arduino/ESP32 esteja previamente pareado com o celular, para isso basta ir nas configurações do celular e procurar por Bluetooth, lá você irá encontrar o dispositivo e clicar em parear.

### Após o pareamento, entre no aplicativo e clique em conectar, irá aparecer uma lista dos dispositivos pareados com o celular, selecione o Arduino/ESP32 e ele irá conectar automaticamente.

<p align="center">
<!-- Listagem de dispositivos aqui -->
</p>

### Após a conexão, basta clicar nos botões para ligar e desligar os comodos da casa.

<p align="center">
<!-- Botões aqui -->
</p>

<br>

## Nota importante 📝

### Você pode alterar os caracteres que são enviados para o Arduino/ESP32, para isso basta segurar o botão desejado por alguns segundos, irá aparecer uma caixa de texto para você alterar o caractere, após alterar, basta clicar em salvar e o caractere será alterado.

<p align="center">
<!-- Alterar caracteres aqui -->
</p>

<br>

## Origem do projeto 🦖

### Este projeto faz parte do meu TCC onde foi desenvolvido uma cidade automatizada em conjunto com um [sistema de monitoramento e controle de irrigação](https://github.com/pedrofnseca/copanga) de uma plantação com solos de diferentes tipos.
