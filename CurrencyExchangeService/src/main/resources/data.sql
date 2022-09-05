create table currency_exchange ( id bigint not null auto_increment, conversion_multiple decimal(19,2), environment varchar(255), source varchar(255), target varchar(255), primary key (id));
insert into currency_exchange (id, source, target, conversion_multiple) values (1001, 'USD', 'INR', 65);
insert into currency_exchange (id, source, target, conversion_multiple) values (1002, 'EUR', 'INR', 75);
insert into currency_exchange (id, source, target, conversion_multiple) values (1003, 'AUD', 'INR', 25);