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
<img width="150" src="https://user-images.githubusercontent.com/97262778/197307331-08413648-c5a5-4571-bbc9-9f4983b9fd11.png">
</p>

<br>

## Manual de funcionamento 📖

### Antes de abrir o aplicativo, é necessário que o Arduino/ESP32 esteja previamente pareado com o celular, para isso basta ir nas configurações do celular e procurar por Bluetooth, lá você irá encontrar o dispositivo e clicar em parear.

### Após o pareamento, entre no aplicativo e clique em conectar, irá aparecer uma lista dos dispositivos pareados com o celular, selecione o Arduino/ESP32 e ele irá conectar automaticamente.

<p align="center">
<img width="250" src="https://user-images.githubusercontent.com/97262778/197307125-f9326abe-ee4d-498e-97f4-8018471c96d2.jpeg">
</p>

### Após a conexão, basta clicar nos botões para ligar e desligar os comodos da casa.

<p align="center">
<img width="250" src="https://user-images.githubusercontent.com/97262778/197307200-143767fe-21c5-46e3-a73c-0567ae5e08a2.jpeg">
</p>

<br>

## Nota importante 📝

### Você pode alterar os caracteres que são enviados para o Arduino/ESP32, para isso basta segurar o botão desejado por alguns segundos, irá aparecer uma caixa de texto para você alterar o caractere, após alterar, basta clicar em salvar e o caractere será alterado.

<p align="center">
<img width="250" src="https://user-images.githubusercontent.com/97262778/197307226-e4df5ad9-25c1-4fb3-af42-801ee1d7cbb8.jpeg">
</p>

<br>

## Origem do projeto 🦖

### Este projeto faz parte do meu TCC onde foi desenvolvido uma cidade automatizada em conjunto com um [sistema de monitoramento e controle de irrigação](https://github.com/pedrofnseca/copanga) de uma plantação com solos de diferentes tipos.

<br>

---

## Arquivo para instalação do aplicativo 📁

### O arquivo .apk do aplicativo está disponivel na pasta [releases](./app/release) do projeto.

<br>

## Como instalar o aplicativo? 📲

### Para instalar o aplicativo é nescessario seguir os seguintes passos:

> #### Ou somente baixar o aquivo em .zip no github e seguir até a pasta ```app/release``` e encontrar o arquivo ```Copanga House.apk``` e instalar no celular.

#### 1. Clone o pojeto em sua máquina via terminal com suporte à git 
```
git clone https://github.com/PedroFnseca/Copanga-House.git
```

#### 2. Entre no projeto que foi clonado 
```
cd Copanga-House
```

##### 3. Abra a pasta do .apk 
```
cd app/release
```

#### 4. Transfira o arquivo ```Copanga House.apk``` para o celular

#### 5. Instale o aplicativo no celular (OBS: É nescessario que o celular tenha as configurações de instalação de aplicativos de fontes desconhecidas ativadas e desative o Play Protect)

#### 6. Comece a utilizar e seja feliz 😁

---