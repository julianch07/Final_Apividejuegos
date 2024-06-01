
// VentasList.jsx
import api from "../../api.jsx";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const VentasList = () => {
  const [ventas, setVentas] = useState([]);

  useEffect(() => {
    const fetchVentas = async () => {
      try {
        const response = await api.get('/ventas');
        setVentas(response.data);
      } catch (error) {
        console.error("Error al obtener las ventas:", error);
      }
    };

    fetchVentas();
  }, []);

  return (
    <div>
      <h1>Ventas</h1>
      <Link to="/agregar">Agregar Venta</Link>
      <ul>
        {ventas.map((venta) => (
          <li key={venta.id}>
             <Link to={`/api/ventas/${venta.id}`}>Venta {venta.id}</Link> - Total: {venta.total}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default VentasList;
