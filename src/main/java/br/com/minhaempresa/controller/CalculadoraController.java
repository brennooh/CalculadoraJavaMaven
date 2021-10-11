package br.com.minhaempresa.controller;

import br.com.minhaempresa.domain.Operacao;
import br.com.minhaempresa.service.CalculadoraService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/calcular")
public class CalculadoraController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double operandoA = 0;
        double operandoB = 0;
        int operador = 0;

        try{
        operandoA = Double.valueOf(req.getParameter("operandoA"));
        operandoB = Double.valueOf(req.getParameter("operandoB"));
        operador = Integer.valueOf(req.getParameter("operador"));
        } catch (NumberFormatException e){
            resp.getWriter().println("Eh aceito somente numeros inteiros");
        } catch (NullPointerException e){
            resp.getWriter().println("Operador invalido.");
        }
        double resultado = 0;

        CalculadoraService calculadoraService = new CalculadoraService();
        Operacao operacao = null;
        switch (operador){
            case 0 :{
                operacao = Operacao.SOMA;
                break;
            }
            case 1: {
                operacao = Operacao.SUBTRACAO;
                break;
            }
            case 2: {
                operacao = Operacao.MULTIPLICACAO;
                break;
            }
            case 3: {
                operacao = Operacao.DIVISAO;
                break;
            }
            default: {}
        }
        try{
        resultado = calculadoraService.calcular(operandoA, operandoB, operacao);
        resp.getWriter().println(resultado);
        } catch (NullPointerException e){
            resp.getWriter().println("Operador invalido.Utilize de 0 a 3!");
        } catch (ArithmeticException e){
            resp.getWriter().println("Nao pode ser divido por 0!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Ta on");
    }
}


