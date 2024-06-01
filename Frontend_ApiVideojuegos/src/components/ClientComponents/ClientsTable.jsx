import ClientsRow from "./ClientsRow";

function ClientsTable({ clientes, onEdit, onDelete }) {
  return (
    <table>
      <thead>
        <tr>
          <th>Nombres</th>
          <th>Email</th>
          <th>Teléfono</th>
          <th>Dirección</th>
          <th>Contraseña</th>
          <th>Número de Identificación</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        {clientes.map((cliente) => (
          <ClientsRow
            key={cliente.clienteId}
            cliente={cliente}
            onEdit={onEdit}
            onDelete={onDelete}
          />
        ))}
      </tbody>
    </table>
  );
}

export default ClientsTable;