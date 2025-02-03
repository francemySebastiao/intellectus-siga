package ao.co.intellectus.servico.guias;

import org.springframework.core.convert.converter.Converter;

import ao.co.intellectus.DTO.GuiaPagamentoCodigo;

public class IntegerToGuiaPagamentoCodigoConverter implements Converter<Integer, GuiaPagamentoCodigo>{

    @Override
    public GuiaPagamentoCodigo convert(Integer source) {
        // TODO Auto-generated method stub
        return new GuiaPagamentoCodigo(source);
    }
    
}
