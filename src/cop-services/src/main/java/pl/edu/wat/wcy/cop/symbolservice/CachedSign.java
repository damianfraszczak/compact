package pl.edu.wat.wcy.cop.symbolservice;

import java.awt.image.BufferedImage;
import java.util.Date;
// Represents cached sign.

public class CachedSign {
    private BufferedImage img;
    private String code;
    private Date date;
    private SymbolType symbolType;

    public CachedSign(BufferedImage img, SymbolType symbolType, String code) {
        super();
        this.img = img;
        this.symbolType = symbolType;
        this.code = code;
        date = new Date();
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public void setSymbolType(SymbolType symbolType) {
        this.symbolType = symbolType;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((symbolType == null) ? 0 : symbolType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CachedSign other = (CachedSign) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return symbolType == other.symbolType;
    }

}
