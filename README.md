# ONLYOFFICE Android App
## Описание
Данное приложение является прототипом Android-программы для интеграции с OnlyOffice API. Оно позволяет пользователю подключаться к порталу, аутентифицироваться, просматривать список файлов и управлять учетными данными. Приложение построено с использованием Kotlin, MVVM и Retrofit,corutines, dagger2 , и предназначено для демонстрации работы с API OnlyOffice.

## Описание функционала
Экран подключения к порталу
На первом экране пользователь вводит адрес портала, логин и пароль. После этого происходит авторизация на целевом портале с использованием API OnlyOffice. В процессе авторизации отображается индикатор загрузки, а при неудачной авторизации выводится сообщение об ошибке. Также реализована валидация формы ввода данных.

## Доступные учетные данные для тестирования:

Portal: https://testdocspaceportal.onlyoffice.com/
Email: 1one.test901@gmail.com
Password: Testpass123
## Экран списка файлов
После успешной авторизации приложение отображает список файлов в категории "Documents". Реализована навигация по вложенным папкам, позволяющая пользователю просматривать содержимое каждой папки.

Экран профиля
В профиле пользователя отображаются:

Аватар
Имя пользователя
Email

Documents — список документов
Rooms 
Trash
Каждая категория отображает соответствующее содержимое. Переключение между категориями легко и удобно.

## Функция выхода
В любой момент пользователь может выйти из своей учетной записи, что возвращает его на экран входа.

## Особенности реализации
Сохранение состояния при повороте экрана: Все данные, включая состояние экранов и фрагментов, сохраняются при смене ориентации экрана, обеспечивая плавный пользовательский опыт.

Для архитектуры приложения был выбран паттерн MVVM (Model-View-ViewModel), что способствует разделению логики и UI.

Используемые библиотеки:

Retrofit — Okhttp - Coroutines - Dagger2 - Compose

