import { useState } from "react";

function ClientsForm({ onSubmit }) {
  const [nombres, setNombres] = useState("");
  const [email, setEmail] = useState("");
  const [telefono, setTelefono] = useState("");
  const [direccion, setDireccion] = useState("");
  const [contrasena, setContrasena] = useState("");
  const [numeroIdentificacion, setNumeroIdentificacion] = useState("");

  const handleNombresChange = (event) => {
    setNombres(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handleTelefonoChange = (event) => {
    setTelefono(event.target.value);
  };

  const handleDireccionChange = (event) => {
    setDireccion(event.target.value);
  };

  const handleContrasenaChange = (event) => {
    setContrasena(event.target.value);
  };

  const handleNumeroIdentificacionChange = (event) => {
    setNumeroIdentificacion(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    onSubmit({
      nombres,
      email,
      telefono,
      direccion,
      contrasena,
      numeroIdentificacion,
    });
    setNombres("");
    setEmail("");
    setTelefono("");
    setDireccion("");
    setContrasena("");
    setNumeroIdentificacion("");
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Nombres"
        value={nombres}
        onChange={handleNombresChange}
        required
      />
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={handleEmailChange}
        required
      />
      <input
        type="text"
        placeholder="Teléfono"
        value={telefono}
        onChange={handleTelefonoChange}
        required
      />
      <input
        type="text"
        placeholder="Dirección"
        value={direccion}
        onChange={handleDireccionChange}
        required
      />
      <input
        type="password"
        placeholder="Contraseña"
        value={contrasena}
        onChange={handleContrasenaChange}
        required
      />
      <input
        type="text"
        placeholder="Número de Identificación"
        value={numeroIdentificacion}
        onChange={handleNumeroIdentificacionChange}
        required
      />
      <button type="submit">Guardar</button>
    </form>
  );
}

export default ClientsForm;