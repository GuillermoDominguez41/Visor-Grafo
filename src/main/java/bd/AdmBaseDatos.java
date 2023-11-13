package bd;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import modelo.grafo.Vertice;

public class AdmBaseDatos {

    private static final Logger logger = Logger.getLogger(AdmBaseDatos.class.getName());
    private final String bd_Vertices = String.valueOf(AdmBaseDatos.class.getClassLoader().getResource("data/vertice.json"));

    public ArrayList<Vertice> obtenerVertices() {
        ArrayList<Vertice> listaVertices = new ArrayList<>();
        String filePath = "data/vertice.json";
        try (Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filePath))) {
                Type listType = new TypeToken<ArrayList<Vertice>>(){}.getType();
                listaVertices = new Gson().fromJson(reader, listType);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "No se logro abrir archivo Json", e);
        }
        return listaVertices;
    }

    public void guardarVertices(ArrayList<Vertice> vertices) {
        try( FileWriter fileWriter = new FileWriter(bd_Vertices) ){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            fileWriter.write(gson.toJson(vertices));
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "No se logro guardar el archivo Json", e);
        }
    }

}
