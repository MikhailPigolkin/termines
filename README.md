# termines
Используется БД Posgres  
инструкция по установке (Linux Ubuntu 22.04):  
  
sudo apt update  
sudo apt -y install postgresql  
  
После установки СУБД нужно открыть терминал и переключиться на пользователя postgres с помощью команды:  
  
sudo -i -u postgres  
psql  
  
Задать пароль  
(этот пароль используется в конфигурации)  
  
ALTER USER postgres PASSWORD '1234';  

создание БД:  

createdb guides  

После запуска рабочий роут:  
localhost:8080/api/v1/download  
метод POST  
если через постман  
![image](https://github.com/MikhailPigolkin/termines/assets/36242761/2c12f2d0-326f-4040-8147-f5afb27663ee)

принимает текстовый файл (.txt) со строками:  
на каждой строке <термин> <делимитер> <описание>  
в качестве разделителя используется "-" дефис  
  
Респонс - текстовый файл с суфиксом (имя загружаемого файла)_written.txt  
Но в этом файле термины уже будут отсортированы по алфавиту.  
