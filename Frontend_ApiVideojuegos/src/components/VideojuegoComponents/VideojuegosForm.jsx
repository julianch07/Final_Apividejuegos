// VideojuegosForm.jsx
import { useState } from "react";

function VideojuegosForm({ onSubmit }) {
  const [codigo, setCodigo] = useState("");
  const [precio, setPrecio] = useState("");
  const [titulo, setTitulo] = useState("");
  const [genero, setGenero] = useState("");
  const [plataforma, setPlataforma] = useState("");
  const [desarrollador, setDesarrollador] = useState("");
  const [descripcion, setDescripcion] = useState("");

  const handleCodigoChange = (event) => {
    setCodigo(event.target.value);
  };

  const handlePrecioChange = (event) => {
    setPrecio(event.target.value);
  };

  const handleTituloChange = (event) => {
    setTitulo(event.target.value);
  };

  const handleGeneroChange = (event) => {
    setGenero(event.target.value);
  };

  const handlePlataformaChange = (event) => {
    setPlataforma(event.target.value);
  };

  const handleDesarrolladorChange = (event) => {
    setDesarrollador(event.target.value);
  };

  const handleDescripcionChange = (event) => {
    setDescripcion(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit({
      codigo,
      precio,
      titulo,
      genero,
      plataforma,
      desarrollador,
      descripcion,
    });
    setCodigo("");
    setPrecio("");
    setTitulo("");
    setGenero("");
    setPlataforma("");
    setDesarrollador("");
    setDescripcion("");
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Código"
        value={codigo}
        onChange={handleCodigoChange}
        required
      />
      <input
        type="number"
        placeholder="Precio"
        value={precio}
        onChange={handlePrecioChange}
        required
      />
      <input
        type="text"
        placeholder="Título"
        value={titulo}
        onChange={handleTituloChange}
        required
      />
      <input
        type="text"
        placeholder="Género"
        value={genero}
        onChange={handleGeneroChange}
        required
      />
      <input
        type="text"
        placeholder="Plataforma"
        value={plataforma}
        onChange={handlePlataformaChange}
        required
      />
      <input
        type="text"
        placeholder="Desarrollador"
        value={desarrollador}
        onChange={handleDesarrolladorChange}
        required
      />
      <input
        type="text"
        placeholder="Descripción"
        value={descripcion}
        onChange={handleDescripcionChange}
        required
      />
      <button type="submit">Guardar</button>
    </form>
  );
}

export default VideojuegosForm;