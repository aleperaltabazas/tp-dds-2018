package DDS.SGE.Dispositivo;

import javax.persistence.*;

public enum MetodoDeCreacion {
    AIRE() {
        public String toString() {
            return "Aire acondicionado";
        }

        private int frigorias = 2000;

        public TipoDispositivo construirPorTipo() {
            return factory.crearAire(frigorias, this.getConsumo());
        }
    },
    TELEVISION() {
        private int pulgadas = 30;

        public TipoDispositivo construirPorTipo() {
            if (this.esInteligente())
                return factory.crearTVBoba(this.getConsumo());
            else
                return factory.crearLED(pulgadas, this.getConsumo());
        }
    },
    HELADERA() {
        private boolean freezer = false;

        public TipoDispositivo construirPorTipo() {
            return factory.crearHeladera(this.getConsumo(), freezer);
        }
    },
    LAVARROPAS() {
        public TipoDispositivo construirPorTipo() {
            if (this.esInteligente())
                return factory.crearLavarropasBobo(this.getConsumo());
            else
                return factory.crearLavarropasInteligente();
        }
    },
    LAMPARA() {
        private int watts = 60;

        public TipoDispositivo construirPorTipo() {
            if (this.esInteligente())
                return factory.crearLampara(watts);
            else
                return factory.crearHalogena(watts);
        }
    },
    COMPUTADORA {
        //TODO: cambiar el factory para que tome la notebook
        private boolean notebook = false;

        public TipoDispositivo construirPorTipo() {
            return factory.crearPCDesktop();
        }
    },
    PLANCHA {
        public TipoDispositivo construirPorTipo() {
            return factory.crearPlancha();
        }
    };

    private String nombre;
    private double consumo;
    private boolean bajoConsumo;
    private boolean inteligente;

    @Transient
    DispositivoFactory factory = new DispositivoFactory();

    public Dispositivo construir() {
        return new Dispositivo(nombre, this.construirPorTipo(), bajoConsumo);
    }

    public abstract TipoDispositivo construirPorTipo();

    public double getConsumo() {
        return consumo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public void setBajoConsumo(boolean bajoConsumo) {
        this.bajoConsumo = bajoConsumo;
    }

    public boolean esInteligente() {
        return inteligente;
    }

    public void setInteligente(boolean inteligente) {
        this.inteligente = inteligente;
    }

    public void fill(String nombre, double consumo, boolean bajoConsumo, boolean inteligente) {
        this.nombre = nombre;
        this.consumo = consumo;
        this.bajoConsumo = bajoConsumo;
        this.inteligente = inteligente;
    }

    public static MetodoDeCreacion parse(String metodo) {
        String ignoreCase = metodo.toLowerCase();

        if (ignoreCase.contains("aire") || ignoreCase.contains("acondicionado")) {
            return AIRE;
        } else if (ignoreCase.contains("tele") || ignoreCase.contains("tv") || ignoreCase.contains("led") || ignoreCase.contains("led") || ignoreCase.contains("ctr")) {
            return TELEVISION;
        } else if (ignoreCase.contains("heladera") || ignoreCase.contains("refrigerador")) {
            return HELADERA;
        } else if (ignoreCase.contains("lavarropas")) {
            return LAVARROPAS;
        } else if (ignoreCase.contains("lamp")) {
            return LAMPARA;
        } else if (ignoreCase.contains("pc") || ignoreCase.contains("compu")) {
            return COMPUTADORA;
        } else if (ignoreCase.contains("plancha")) {
            return PLANCHA;
        }

        throw new RuntimeException("Por favor, ingrese un fabricante válido");
    }

    public String toString() {
        String input = super.toString().toLowerCase();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}

