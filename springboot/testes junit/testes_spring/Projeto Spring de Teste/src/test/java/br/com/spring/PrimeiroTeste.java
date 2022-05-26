package br.com.spring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PrimeiroTeste {

    @Mock
    private Calculadora calculadora;

    @BeforeEach
    public void setUpt(){
        calculadora = new Calculadora();
    }

    @Test
    public void deveSomar2NumerosTeste(){

        int numero1 = 10, numero2 = 5;

        int resultado = calculadora.somar(numero1, numero2);

        //verificacoes
        Assertions.assertThat(resultado).isBetween(14, 16);
        Assertions.assertThat(resultado).isEqualTo(15);

    }

    @Test
    public void naoDeveSomarNumeroNegativos(){
        //cenario
        Calculadora calculadora = new Calculadora();

        int num1 = -10, num2 = 5;

        assertThrows(RuntimeException.class, () -> calculadora.somar(num1, num2));

    }
}

class Calculadora{

    int somar(int num, int num2){

        if(num < 0 || num2 < 0){
            throw new RuntimeException("Não é permitido somar numero negativos");
        }
        return num +  num2;
    }
}
