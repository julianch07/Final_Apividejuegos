function VideojuegosRow({ videojuego, onEdit, onDelete }) {
  const handleEdit = () => {
    onEdit(videojuego);
  };

  const handleDelete = () => {
    onDelete(videojuego.id);
  };

  return (
    <tr>
      <td>{videojuego.codigo}</td>
      <td>{videojuego.precio}</td>
      <td>{videojuego.titulo}</td>
      <td>{videojuego.genero}</td>
      <td>{videojuego.plataforma}</td>
      <td>{videojuego.desarrollador}</td>
      <td>{videojuego.descripcion}</td>
      <td>
        <button onClick={handleEdit}>Editar ✏️</button>
        <button onClick={handleDelete}>Eliminar 🗑️</button>
      </td>
    </tr>
  );
}

export default VideojuegosRow;