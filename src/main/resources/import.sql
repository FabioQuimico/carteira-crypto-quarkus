insert into clientes(id, nome) values(1, 'Fabio');
insert into clientes(id, nome) values(2, 'Alexandre');
insert into clientes(id, nome) values(3, 'Rafael');
insert into clientes(id, nome) values(4, 'Gabriel');

insert into cryptos(codigo, nome, valorCompra, valorVenda) 
   values('BTC', 'Bitcoin', 2000.0, 1999.0);

insert into crypto_cliente(id, idCliente, codigoCrypto, quantidade)
   values(1, 1, 'BTC', 10.0);