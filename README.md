Здравствуйте! 
Это мой результат тестового задания в конкурсе на участие в ШИФТЛАБ-2022
В приложении используется фреймворк Spring Boot, H2 JDBC Driver (только изучаю Hibernate,
поэтому, на всякий случай, решил его не использовать).  

Также имеются модульные тесты для слоя репозиториев. 

Инструкция к использованию api: 

Базовый URL для всех запросов: localhost:8080/api
1. Добавление товаров;
   Реализовано с помощью POST-запросов
    1. /desktop/add-new - добавляет новый десктоп (RequestBody в формате Json, в котором описаны поля:
       serialNumber - серийный номер (String), price - цена товара (Big Decimal), quantityInStock - количество
       в наличии (int), manufacturerName - имя производителя (String), formFactor - форм-фактор (1, 2, 3) (Byte))
       1 - десктоп 
       2 - неттоп 
       3 - моноблок
    2. /monitor/add-new - добавляет новый монитор (RequestBody в формате Json, в котором описаны поля:
       serialNumber - серийный номер (String), price - цена товара (Big Decimal), quantityInStock - количество
       в наличии (int), manufacturerName - имя производителя (String), diagonal - диагональ монитора
       (Short))
    3. /hard-disk/add-new - добавляет новый жесткий диск (RequestBody в формате Json, в котором описаны поля:
       serialNumber - серийный номер (String), price - цена товара (Big Decimal), quantityInStock - количество
       в наличии (int), manufacturerName - имя производителя (String), capacity - емкость жесткого диска в Гб
       (int))
    4. /laptop/add-new - добавляет новый ноутбук (RequestBody в формате Json, в котором описаны поля:
       serialNumber - серийный номер (String), price - цена товара (Big Decimal), quantityInStock - количество
       в наличии (int), manufacturerName - имя производителя (String), size - размер экрана (13,14,15 или 17)
       (Byte))
2. Удаление товаров: 
    POST-запрос: 
    /common/delete - удаляет товар (параметры: int id)
3. Редактирование товаров: 
    Реализовано с помощью POST-запросов
    1. /common/set-quantity - устанавливает количество товара в наличии (параметры: int id, int quantity)
    2. /common/increase-quantity - увеличивает количество товара в наличии (параметры: int id, int amount)
    3. /common/increase-quantity - уменьшает количество товара в наличии (параметры: int id, int amount)
    4. /common/set-price - устанавливает цену товара (параметры: int id, BigDecimal price)
    5. /common/set-serial-number - устанавливает серийный номер товара (параметры: int id, String serialNumber)
4. Просмотр всех существующих товаров по типу
    Реализовано с помощью GET-запросов
    1. /monitor/get-all - возвращает все мониторы (без параметров) 
    2. /desktop/get-all - возвращает все десктопы (без параметров)
    3. /hard-disk/get-all - возвращает все жесткие диски (без параметров)
    4. /monitor/get-all - возвращает все мониторы (без параметров)
5. Просмотр товара по идентификатору
   GET-запрос
   /common/get-by-id - возвращает произвольный товар (параметры - int id)


