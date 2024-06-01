// VentaDetails.jsx
import api from "../../api.jsx";
import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

const VentaDetails = () => {
  const { id } = useParams();
  const [venta, setVenta] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchVenta = async () => {
      try {
        const response = await api.get(`/ventas/${id}`);
        setVenta(response.data);
      } catch (error) {
        console.error("Error al obtener la venta:", error);
      }
    };

    fetchVenta();
  }, [id]);

  const handleDelete = async () => {
    try {
      await api.delete(`/ventas/${id}`);
      navigate("/");
    } catch (error) {
      console.error("Error al eliminar la venta:", error);
    }
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      await api.put(`/ventas/${id}`, venta);
      setIsEditing(false);
    } catch (error) {
      console.error("Error al actualizar la venta:", error);
    }
  };

  if (!venta) return <div> Cargando...</div>;

  return (
    <div>
      <h1>Detalles de la Venta</h1>
      {isEditing ? (
        <form onSubmit={handleUpdate}>
          <div>
            <label>Total:</label>
            <input
              type="number"
              step="0.01"
              value={venta.total}
              onChange={(e) =>
                setVenta({ ...venta, total: parseFloat(e.target.value) })
              }
              required
            />
          </div>
          <button type="submit">Actualizar Venta</button>
        </form>
      ) : (
        <div>
          <p>ID: {venta.id}</p>
          <p>ID del Cliente: {venta.cliente.clienteId}</p>
          <p>Total: {venta.total}</p>
          <button onClick={() => setIsEditing(true)}>Editar</button>
          <button onClick={handleDelete}>Eliminar</button>
        </div>
      )}
    </div>
  );
};

export default VentaDetails;