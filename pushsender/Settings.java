package pushsender;

public class Settings {
	private String[] region = new String[19];
	public Settings() {
		
	}
	public String[] loadText(int textIni, int textFin) { 
		String[] aux = new String[(textFin + 1) - textIni]; 
		int j = 0;
		for (int i = textIni; i < textFin + 1; i++) { 
			aux[j] = region[i]; 
			j++;
		}
		return aux;
	}
	public void setRegion(String region) {
		switch(region) {
			case "ES":
			this.region = new String[] { "Crear notificacion", "Mandar notificacion", "Salir", "¿Quiéres crear o mandar notificaciones push?", 
					"Escribe el titulo del mensaje", "Escribe el contenido del mensaje", "Escribe la URL de lanzamiento del mensaje", "Escribe la imagen adjunta al mensaje", "Salir", "Guardar", "No puedes dejar ningun campo vacío", "Se ha guardado correctamente", "Hubo un problema al guardar el archivo", 
					"Atrás", "Enviar", "Se ha enviado correctamente la notificacion", "Hubo un error al enviar la notificacion", "No puedes enviar 0 notificaciones"};
			break;
			case "FR":
			this.region = new String[] { "Créer notification", "Envoyer notification", "Exit", "Tu veux créer une push notifications?",
					"Ecrire le titre du message", "Ecrire le message", "Mettre URL de lancment", "Ecrire le message avec image URL", "Exit", "Sauvegarger", "Tu ne peux pas laisser un champs vide", "Sauvegardé avec succès", "Erreur de sauvegarde du fichier", 
					"Retour", "Envoyer", "Envoyé avec succès", "Erreur lors de l'envoi de la notification", "Tu ne peux pas envoyer 0 notification"};
			break;
			case "DE":
			this.region = new String[] { "Crear notificacion", "Mandar notificacion", "Salir", "¿Quiéres crear o mandar notificaciones push?", "Selecciona el titulo del mensaje", "No tienes ninguna notificación guardada", "Escribe el titulo del mensaje", "Escribe el contenido del mensaje", "Escribe la URL de lanzamiento del mensaje", "Escribe la imagen adjunta al mensaje" };
			break;
			case "DK":
			this.region = new String[] { "Crear notificacion", "Mandar notificacion", "Salir", "¿Quiéres crear o mandar notificaciones push?", "Selecciona el titulo del mensaje", "No tienes ninguna notificación guardada", "Escribe el titulo del mensaje", "Escribe el contenido del mensaje", "Escribe la URL de lanzamiento del mensaje", "Escribe la imagen adjunta al mensaje" };
			break;
			case "IT":
			this.region = new String[] { "Crear notificacion", "Mandar notificacion", "Salir", "¿Quiéres crear o mandar notificaciones push?", "Selecciona el titulo del mensaje", "No tienes ninguna notificación guardada", "Escribe el titulo del mensaje", "Escribe el contenido del mensaje", "Escribe la URL de lanzamiento del mensaje", "Escribe la imagen adjunta al mensaje" };
			break;
			case "NL":
			this.region = new String[] { "Crear notificacion", "Mandar notificacion", "Salir", "¿Quiéres crear o mandar notificaciones push?", "Selecciona el titulo del mensaje", "No tienes ninguna notificación guardada", "Escribe el titulo del mensaje", "Escribe el contenido del mensaje", "Escribe la URL de lanzamiento del mensaje", "Escribe la imagen adjunta al mensaje" };
			break;
			case "RU":
			this.region = new String[] { "Crear notificacion", "Mandar notificacion", "Salir", "¿Quiéres crear o mandar notificaciones push?", "Selecciona el titulo del mensaje", "No tienes ninguna notificación guardada", "Escribe el titulo del mensaje", "Escribe el contenido del mensaje", "Escribe la URL de lanzamiento del mensaje", "Escribe la imagen adjunta al mensaje" };
			break;
			case "SE":
			this.region = new String[] { "Crear notificacion", "Mandar notificacion", "Salir", "¿Quiéres crear o mandar notificaciones push?", "Selecciona el titulo del mensaje", "No tienes ninguna notificación guardada", "Escribe el titulo del mensaje", "Escribe el contenido del mensaje", "Escribe la URL de lanzamiento del mensaje", "Escribe la imagen adjunta al mensaje" };
			break;
			case "TR":
			this.region = new String[] { "Crear notificacion", "Mandar notificacion", "Salir", "¿Quiéres crear o mandar notificaciones push?", "Selecciona el titulo del mensaje", "No tienes ninguna notificación guardada", "Escribe el titulo del mensaje", "Escribe el contenido del mensaje", "Escribe la URL de lanzamiento del mensaje", "Escribe la imagen adjunta al mensaje" };
			break;
			case "US":
			this.region = new String[] { "Crear notificacion", "Mandar notificacion", "Salir", "¿Quiéres crear o mandar notificaciones push?", "Selecciona el titulo del mensaje", "No tienes ninguna notificación guardada", "Escribe el titulo del mensaje", "Escribe el contenido del mensaje", "Escribe la URL de lanzamiento del mensaje", "Escribe la imagen adjunta al mensaje" };
			break;
			default:
			this.region = new String[] { "Crear notificacion", "Mandar notificacion", "Salir", "¿Quiéres crear o mandar notificaciones push?", "Selecciona el titulo del mensaje", "No tienes ninguna notificación guardada", "Escribe el titulo del mensaje", "Escribe el contenido del mensaje", "Escribe la URL de lanzamiento del mensaje", "Escribe la imagen adjunta al mensaje" };
			break;
		}
	}

	public int getRegionLength() {
		return this.region.length;
	}

	
}
