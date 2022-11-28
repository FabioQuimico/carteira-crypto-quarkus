insert into clientes(id, nome) values(1, 'Fabio');
insert into clientes(id, nome) values(2, 'Alexandre');
insert into clientes(id, nome) values(3, 'Rafael');
insert into clientes(id, nome) values(4, 'Gabriel');

insert into cryptos(codigo, nome, valorCompra, valorVenda) 
   values('BTC', 'Bitcoin', 16210.6, 16000);
insert into cryptos(codigo, nome, valorCompra, valorVenda) 
   values('ETH', 'Ethereum', 1127.34, 1100.0);
insert into cryptos(codigo, nome, valorCompra, valorVenda) 
   values('USDT', 'Tether', 0.9996, 0.9);
insert into cryptos(codigo, nome, valorCompra, valorVenda) 
   values('ADA', 'Cardano', 0.3, 0.2);
insert into cryptos(codigo, nome, valorCompra, valorVenda) 
   values('USDC', 'USD Coin', 2000.0, 1999.0);

insert into crypto_cliente(id, idCliente, codigoCrypto, quantidade)
   values(1, 1, 'BTC', 10.0);
