package DDS.SGE.Dispositivo;

import javax.persistence.*;

public enum MetodoDeCreacion {
    AIRE() {
        private int frigorias = 2000;

        public TipoDispositivo construirPorTipo() {
            return factory.crearAire(frigorias, this.getConsumo());
        }
    },
    TELE() {
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
}

