
// AddVenta.jsx
import api from "../../api.jsx";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const AddVenta = () => {
  const [clienteId, setClienteId] = useState("");
  const [videojuegosIds, setVideojuegosIds] = useState("");
  const [total, setTotal] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const videojuegosIdsArray = videojuegosIds
        .split(",")
        .map((id) => parseInt(id.trim()));
      await api.post("/ventas", {
        clienteId: parseInt(clienteId),
        videojuegosIds: videojuegosIdsArray,
        total: parseFloat(total),
      });
      navigate("/");
    } catch (error) {
      console.error("Error al agregar la venta:", error);
    }
  };

  return (
    <div>
      <h1>Agregar Venta</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>ID del Cliente:</label>
          <input
            type="number"
            value={clienteId}
            onChange={(e) => setClienteId(e.target.value)}
            required
          />
        </div>
        <div>
          <label>IDs de Videojuegos (separados por coma):</label>
          <input
            type="text"
            value={videojuegosIds}
            onChange={(e) => setVideojuegosIds(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Total:</label>
          <input
            type="number"
            step="0.01"
            value={total}
            onChange={(e) => setTotal(e.target.value)}
            required
          />
        </div>
        <button type="submit">Agregar Venta</button>
      </form>
    </div>
  );
};

export default AddVenta;