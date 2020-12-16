package eapli.base.productionlinemanagement.domain;

public enum EstadoProcessamento {
    DESATIVO {
        public String toString() {
            return "Desativo";
        }
    },
    ATIVO {
        public String toString() {
            return "Ativo";
        }
    }
}
