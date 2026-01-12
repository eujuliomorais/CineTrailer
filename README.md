[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/AR7CADm8)
[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=21013370)
# Cine Trailer 📱🎬

## Descrição do Projeto ⚙️
O CineTrailer é um aplicativo voltado para amantes de filmes que desejam descobrir novas produções e acompanhar lançamentos de forma prática e interativa. 

O aplicativo reúne trailers, sinopses, avaliações e informações sobre cada obra, permitindo ao usuário explorar diferentes gêneros e encontrar rapidamente algo que combine com seu gosto.

Será comsumida a API externa The Movie DB (https://www.themoviedb.org/)


## Funcionalidades Principais 👨‍💻

 - Exibição de Trailers: Integração com YouTube para reprodução dinâmica de trailers.
 -  Busca Inteligente: Pesquisa de filmes e séries por nome e categorias (Em cartaz, Populares e Em breve).
 -  Notificações Diárias: Sistema de sugestões automáticas de filmes usando WorkManager.
 -  Persistência de Favoritos: Salve seus filmes preferidos localmente utilizando Room Database.
 -  Segurança de API: Uso de Interceptors e BuildConfig para proteger as chaves de acesso.

##  Tecnologias 🔨
 - Linguagem	Kotlin 
 - Interface (UI)	Jetpack Compose
 - Rede	Retrofit, OkHttp (Interceptors)
 - Imagens	Coil (AsyncImage)
 - Banco de Dados	Room
 - Arquitetura	ViewModel, LiveData/State, Navigation Compose
 - Background	WorkManager

## Equipe 👥
*  <strong>Júlio Mateus Morais<strong> (563850) [![github](https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/eujuliomorais)
*  <strong>Pablo Vinicios da Silva Araujo<strong> (574229) [![github](https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/PabloVini28)

## Instruções para Execução 💻

```bash
# Clone o repositório
git clone https://github.com/eujulimorais/cinetrailer.git

# Configure a Chave da API no local.properties
TMDB_API_KEY=sua_chave_aqui_sem_aspas

# Build e Run:

    Abra o projeto no Android Studio

    Sincronize o Gradle.

    Execute o app em um emulador ou dispositivo físico com API 24 (Android 7.0) ou superior.
