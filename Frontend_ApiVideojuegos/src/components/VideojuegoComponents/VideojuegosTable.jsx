// VideojuegosTable.jsx
import VideojuegosRow from "./VideojuegosRow";

function VideojuegosTable({ videojuegos, onEdit, onDelete }) {
  console.log("Videojuegos para renderizar:", videojuegos);

  if (!Array.isArray(videojuegos)) {
    return <div> No se encontraron videojuegos</div>;
  }

  return (
    <table>
      <thead>
        <tr>
          <th>Código</th>
          <th>Precio</th>
          <th>Título</th>
          <th>Género</th>
          <th>Plataforma</th>
          <th>Desarrollador</th>
          <th>Descripción</th>
          { <th>Acciones</th>}
        </tr>
      </thead>
      <tbody>
        {videojuegos.map((videojuego, index) => (
          <VideojuegosRow
            key={index}
            videojuego={videojuego}
            onEdit={onEdit}
            onDelete={onDelete}
          />
        ))}
      </tbody>
    </table>
  );
}

export default VideojuegosTable;