# TestTaskAvito
### Описание работы приложения
Для корректной работы приложения необходима база данных PostgreSQL, в которую необходимо импортировать таблицы, хранящиеся в директории dbTables.

Приложение представляет собой сервис, принимающий HTTP POST запросы, хранящие параметры в формате JSON, выполнение запроса осуществляется вызовом метода doPost();

Связь с базой данных осуществляется посредством библиотеки Hibernate.

Для корректной работы приложения необходимо в файле hibernate.cfg.xml в директории src/main/resources указать порт базы данных, имя пользователя и пароль.
### Параметры HTTP POST запросов
#### Создание встречи
* Параметр "action" должен быть установлен "createMeeting"
* Параметр "date" должен хранить дату встречи в строковом формате
* Параметр "time" должен хранить время встречи в строковом формате
#### Удаление встречи
* Параметр "action" должен быть установлен "deleteMeeting"
* Параметр "meetingId" должен хранить идентификатор встречи, которую необходимо удалить
#### Добавление нового участника во встречу
* Параметр "action" должен быть установлен "addNewParticipant"
* Параметр "meetingId" должен хранить идентификатор встречи, в которую необходимо добавить участника
* Параметр "name" должен хранить имя участника
* Параметр "surname" должен хранить фамилию участника
* Параметр "email" должен хранить электронную почту участника
#### Добавление существующего участника во встречу
* Параметр "action" должен быть установлен "addParticipantById"
* Параметр "meetingId" должен хранить идентификатор встречи, в которую необходимо добавить участника
* Параметр "participantId" должен хранить идентификатор участника, которого необходимо добавить
#### Удаление участника из встречи
* Параметр "action" должен быть установлен "deleteParticipantFromMeeting"
* Параметр "meetingId" должен хранить идентификатор встречи, из которой необходимо удалить участника
* Параметр "participantId" должен хранить идентификатор участника, которого необходимо удалить
#### Вывод в консоль данных всех встреч
* Параметр "action" должен быть установлен "showAll"
