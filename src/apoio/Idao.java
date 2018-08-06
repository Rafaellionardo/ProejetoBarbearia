/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.util.ArrayList;

/**
 *
 * @author renan
 */
public interface Idao {

    ArrayList<Object> objs = new ArrayList<>();
    Object obj = new Object();

    public boolean salvar(Object o);

    public String atualizar(Object o);

    public Boolean excluir(int id);

    public ArrayList<Object> consultarTodos();

    public ArrayList<Object> consultar(String criterio);

    public Object consultarId(int id);
}
