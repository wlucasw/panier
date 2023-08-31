package theodo.panier.Strategy.JSP;

import theodo.panier.Interface.DocumentParameter;
import theodo.panier.Interface.Generator;

public class JSPStrategy implements Generator {
    @Override
    public String generateHtml(DocumentParameter documentParameter){
        return("JSP");
    }
}
