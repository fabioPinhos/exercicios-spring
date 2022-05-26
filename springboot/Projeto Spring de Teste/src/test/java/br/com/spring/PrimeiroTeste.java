import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroTeste {

    @Test
    public void deveSomar2NumerosTeste(){

        int numero1 = 10, numero2 = 5;

        Calculadora calculadora = new Calculadora();

        int resultado = calculadora.somar(numero1, numero2);

        //verificacoes
        Assertions.assertThat(resultado).isBetween(14, 16);
        Assertions.assertThat(resultado).isEqualTo(15);

    }

    @Test(expected = RuntimeException.class)
    public void naoDeveSomarNumeroNegativos(){
        //cenario
        Calculadora calculadora = new Calculadora();

        int num1 = -10, num2 = 5;

        calculadora.somar(num1, num2);
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
