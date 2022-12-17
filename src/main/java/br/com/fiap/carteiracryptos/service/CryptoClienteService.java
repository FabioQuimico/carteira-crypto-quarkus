package br.com.fiap.carteiracryptos.service;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.fiap.carteiracryptos.dto.CryptoClienteDTO;
import br.com.fiap.carteiracryptos.model.CryptoCliente;
import br.com.fiap.carteiracryptos.repository.ClienteRepository;
import br.com.fiap.carteiracryptos.repository.CryptoClienteRepository;
import br.com.fiap.carteiracryptos.repository.CryptoRepository;

@RequestScoped
public class CryptoClienteService {
   
   @Inject
   CryptoClienteRepository repository;
   @Inject
   ClienteRepository clienteRepository;
   @Inject
   CryptoRepository cryptoRepository;


   public List<CryptoCliente> listaCryptoClientes(){
      return repository.findAll().list();
   }

   public List<CryptoCliente> buscaCryptoCliente(Long idCliente){
      return repository.find("idCliente", idCliente).list();
   }

   @Transactional
   public CryptoCliente insereCryptoCliente(CryptoClienteDTO dto) throws SQLException{

      CryptoCliente cc = new CryptoCliente();
      cc.setCliente(clienteRepository.buscarCliente(dto.getIdCliente()));
      cc.setCrypto(cryptoRepository.buscaCrypto(dto.getCodigoCrypto()));
      cc.setQuantidade(dto.getQuantidade());
      repository.persist(cc);
      // cryptoCliente.persist();
      return cc;
   }

   @Transactional
   public CryptoCliente saveCryptoCliente(CryptoCliente cryptoCliente){
      repository.persist(cryptoCliente);  
      return cryptoCliente;
   }

   @Transactional
   public void deletaCryptoCliente(CryptoCliente cryptoCliente){
      repository.delete(cryptoCliente);
   }
}
