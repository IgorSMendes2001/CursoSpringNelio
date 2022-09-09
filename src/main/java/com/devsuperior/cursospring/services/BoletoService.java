package com.devsuperior.cursospring.services;

import com.devsuperior.cursospring.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {
    public void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date instantePedido){
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(instantePedido);
       calendar.add(Calendar.DAY_OF_MONTH,7);
       pagamentoComBoleto.setDataVencimento(calendar.getTime());
    }
}
