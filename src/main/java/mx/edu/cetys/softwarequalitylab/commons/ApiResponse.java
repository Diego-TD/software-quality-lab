package mx.edu.cetys.softwarequalitylab.commons;

public record ApiResponse<T>(String info, T response, String error){}
