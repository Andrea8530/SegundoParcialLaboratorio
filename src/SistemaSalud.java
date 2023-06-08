import java.util.*;

public class SistemaSalud {
    private Integer cantReactivos;
    private LinkedList<Persona> personas;
    private Map<Integer, InfoPaciente> mapPersonas;
    private List<Persona> sanos;
    private List<String> enfermos;


    public SistemaSalud() {
        this.cantReactivos = 2;
        personas = new LinkedList<>();
        mapPersonas = new HashMap<>();
        sanos = new ArrayList<>();
        enfermos = new ArrayList<>();
    }

    public Boolean existeDni(Persona pers){
        for(Persona p: personas){
            if(p.getDni().equals(pers.getDni())){
                return true;
            }
        }
        return false;
    }

    public void agregarPersona (Persona pers) throws FaltaDeRactivosExeption {
        if (!existeDni(pers)){
            if(cantReactivos>0){
                personas.add(pers);
                cantReactivos--;
                System.out.println("Se agrego al paciente: " + pers.getApellido());
            }else throw new FaltaDeRactivosExeption("---ERROR--- falta de reactivos\n");
        }else{
            System.out.printf("Ya existe es dni");
        }
    }

    public void agregarTest(){
        Scanner scanner = new Scanner(System.in);
        String resp;
        System.out.println("Tiene mas test?");
        resp= scanner.nextLine();
        if (resp.equalsIgnoreCase("si")){
            System.out.println("Cuantos reactivos quiere ingresar?");
            cantReactivos = scanner.nextInt();
        }
    }

    public void testear (){
        for(Persona p: personas){
            InfoPaciente paciente = new InfoPaciente(p.getDni(),generarTemperaturaRamdon());
            mapPersonas.put(p.getKit(), paciente);
            if (paciente.getTemperatura()>37){
                enfermos.add("kit= " +p.getKit() +" Temperatura= "+ paciente.getTemperatura().toString());
            }else{
                sanos.add(p);
            }
        }
    }

    public Integer generarTemperaturaRamdon (){
        Random random = new Random();
        Integer temp = random.nextInt(36,40);
        return temp;
    }


    public void aislar2 () {
        for(Map.Entry<Integer, InfoPaciente> map : mapPersonas.entrySet()){
            try {
                if (map.getValue().getTemperatura() > 37) {
                    throw new TemperaturaElevadaExeption("Persona con temperatura= " + map.getKey() + " " + buscarBarrio(map.getValue().getDni()));
                }
            }catch (TemperaturaElevadaExeption e){
                System.out.println(e.getMessage());
            }
        }
    }

    public String buscarBarrio(String dni){
        String buscado = null;
        for(Persona p: personas){
            if(p.getDni().equals(dni)){
                buscado = p.getBarrio();
            }
        }
        return buscado;
    }









    public Map<Integer, InfoPaciente> getMapPersonas() {
        return mapPersonas;
    }

    public void setMapPersonas(Map<Integer, InfoPaciente> mapPersonas) {
        this.mapPersonas = mapPersonas;
    }

    public Integer getCantReactivos() {
        return cantReactivos;
    }

    public void setCantReactivos(Integer cantReactivos) {
        this.cantReactivos = cantReactivos;
    }

    public LinkedList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(LinkedList<Persona> personas) {
        this.personas = personas;
    }

    public List<Persona> getSanos() {
        return sanos;
    }

    public void setSanos(List<Persona> sanos) {
        this.sanos = sanos;
    }

    public List<String> getEnfermos() {
        return enfermos;
    }

    public void setEnfermos(List<String> enfermos) {
        this.enfermos = enfermos;
    }

    @Override
    public String toString() {
        return "SistemaSalud{" +
                "cantReactivos=" + cantReactivos +
                ", personas=" + personas +
                ", mapPersonas=" + mapPersonas +
                ", sanos=" + sanos +
                ", enfermos=" + enfermos +
                '}';
    }
}
