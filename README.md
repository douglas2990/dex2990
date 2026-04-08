# 📱 Dex App - Pokémon Explorer

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![MVVM](https://img.shields.io/badge/Architecture-MVVM-orange?style=for-the-badge)

🌎 Read this in: **Português** | [English](README.en.md)

Enciclopédia Pokémon desenvolvida em **Kotlin**, focada em demonstrar a implementação da arquitetura **MVVM** e o consumo eficiente de APIs REST utilizando a **PokéAPI**.




---

## 🚀 O Projeto

O Dex App é um guia detalhado que permite aos usuários explorar o universo Pokémon. O projeto foi construído para ser um exemplo prático de como estruturar uma aplicação escalável, utilizando componentes do **Android Jetpack** e garantindo uma separação clara de responsabilidades.

## 🏗️ Arquitetura e Estrutura

Para garantir a manutenibilidade e testabilidade, o projeto utiliza o padrão **MVVM (Model-View-ViewModel)**:
- **View:** Camada de interface (Activities/Fragments) que observa as mudanças no ViewModel.
- **ViewModel:** Gerencia os dados relacionados à UI e sobrevive a mudanças de configuração, comunicando-se com a camada de dados.
- **Model (Data):** Responsável pelo consumo da API e gerenciamento das regras de negócio.

## 🛠️ Tecnologias e Bibliotecas

O ecossistema do projeto foi construído com ferramentas consolidadas no mercado:

- **Rede & API:** - [Retrofit](https://square.github.io/retrofit/): Interface para consumo da PokéAPI.
  - [OkHttp](https://square.github.io/okhttp/): Interceptors e gerenciamento eficiente de requisições HTTP.
- **Processamento de Imagens:** - [Fresco](https://frescolib.org/) & [Picasso](https://square.github.io/picasso/): Carregamento inteligente e cache de imagens para otimização de memória.
- **Android Jetpack:**
  - **ViewModel & LiveData:** Para uma UI reativa e ciclo de vida seguro.
  - **View Binding:** Acesso seguro e performático aos componentes de layout.

## 📈 Melhores Práticas Implementadas

- **Separação de Responsabilidades:** Código organizado para facilitar futuras implementações e correções.
- **Manipulação Assíncrona:** Gerenciamento de chamadas de rede para garantir que a UI nunca trave.
- **Gerenciamento de Cache:** Uso de bibliotecas de imagem para reduzir o consumo de dados e melhorar a experiência do usuário.

## 📸 Demonstração

| Listagem | Detalhes Técnicos |
| :---: | :---: |
| <img src="screenshots/lista.png" width="250"> | <img src="screenshots/detalhes.png" width="250"> |



## 🔗 Links Úteis

| 📂 Repositório Principal |
| :--- |
| [![Acessar Branch Master](https://img.shields.io/badge/Acessar_Código_Fonte-Branch_Master-blue?style=for-the-badge&logo=github)](https://github.com/douglas2990/dex2990/tree/master) |

---

## 📥 Download do App

[![Download APK](https://img.shields.io/badge/Download-APK-success?style=for-the-badge&logo=android)](https://github.com/douglas2990/dex2990/releases/download/untagged-9b3fc67c4648bbe5fde3/app-debug.apk)

> **Dica:** Se o download não iniciar, verifique se a versão v1.0.0 foi publicada como "Latest" no GitHub.

---

## 👨‍💻 Desenvolvido por

**Douglas Sousa de Oliveira**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/douglas-sousa-de-oliveira-775a50b3/)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/douglas2990)
