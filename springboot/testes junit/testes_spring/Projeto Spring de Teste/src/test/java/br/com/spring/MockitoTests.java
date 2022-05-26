package br.com.spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MockitoTests {

    @Mock
    List<String> lista;

    @Test
    public void primeiroTesteMockito(){

        Mockito.when(this.lista.size()).thenReturn(20);

        int size = 0;

        if (size == 0) {
            size = this.lista.size();
        }

        System.out.println("Yes babe :::" + size);

        Assertions.assertThat(size).isEqualTo(20);

        //verificar se chamou o lista.size()
        Mockito.verify(lista).size();

        //verifica qntas vezes o lista.size() foi executado
        Mockito.verify(lista, Mockito.times(1)).size();
    }

    @Test
    public void ordemTesteMockito(){
        this.lista.add("");
        this.lista.size();

        InOrder inOrder = Mockito.inOrder(lista);
        inOrder.verify(lista).add("");
        inOrder.verify(lista).size();

    }

}
