# Задание
Напишите программу на языке программирования java, которая прочитает файл tickets.json и рассчитает:

- среднее время полета между городами Владивосток и Тель-Авив
- 90-й процентиль времени полета между городами Владивосток и Тель-Авив

Программа должна вызываться из командной строки Linux, результаты должны быть представлены в текстовом виде. В качестве результата нужно прислать ответы на поставленные вопросы и ссылку на исходный код.


# Установка и запуск
Клонируем репозиторий:

- git clone https://github.com/NikitaNasevich/Test-Task-Idea-Platform.git
- cd Test-Task-Idea-Platform

Собираем jar:

- ./mvnw clean
- ./mvnw package

Запускаем jar:

- java -jar target/TestTaskIdeaPlatform-1.0-SNAPSHOT.jar