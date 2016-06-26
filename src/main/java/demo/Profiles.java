package demo;

public final class Profiles {

	/**
	 * When active, indicates that the application is being deployed to the "production"
	 * server.
	 *
	 * <p>Mutually exclusive with {@link #DEV} profile.</p>
	 */
	public static final String PROD = "prod";

	/**
	 * The default profile for application. Indicates that the application is running locally,
	 * i.e. on a developer machine as opposed to running on production server and should expect
	 * to find data sources, etc in-memory as opposed to finding them in production server services
	 * such as MySQL, RabbitMQ etc.
	 *
	 * <p>Mutually exclusive with {@link #PROD} profile.</p>
	 */
	public static final String DEV = "dev";

}
