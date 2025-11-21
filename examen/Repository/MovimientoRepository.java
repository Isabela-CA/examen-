 // Buscar por rango de fecha limite de 10
 @Query ("SELECT m FROM Movimiento m ORDER BY fecha LIMIT 10")
 List<Movimiento> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
