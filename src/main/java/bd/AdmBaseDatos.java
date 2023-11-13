package main.java.bd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import main.java.modelo.grafo.Vertice;
import main.resources.VARIABLES;

public class AdmBaseDatos {

    private static final Logger logger = Logger.getLogger(AdmBaseDatos.class.getName());
    private final String bd_Vertices = VARIABLES.rutaBdVertice;

    public ArrayList<Vertice> obtenerVertices() {

        ArrayList<Vertice> listaVertices = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(bd_Vertices));
            Type tipoListaVertices = new TypeToken<ArrayList<Vertice>>() {}.getType();

            listaVertices = new Gson().fromJson(bufferedReader, tipoListaVertices);

            bufferedReader.close();
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
