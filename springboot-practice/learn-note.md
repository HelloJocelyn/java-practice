## SpringApplication
### what's been done during the instantiation of SpringApplication 
```
this.sources = new LinkedHashSet();
this.bannerMode = Mode.CONSOLE;
this.logStartupInfo = true;
this.addCommandLineProperties = true;
this.addConversionService = true;
this.headless = true;
this.registerShutdownHook = true;
this.additionalProfiles = new HashSet();
this.isCustomEnvironment = false;
this.resourceLoader = resourceLoader;
Assert.notNull(primarySources, "PrimarySources must not be null");
this.primarySources = new LinkedHashSet(Arrays.asList(primarySources));
this.webApplicationType = WebApplicationType.deduceFromClasspath();
this.setInitializers(this.getSpringFactoriesInstances(ApplicationContextInitializer.class));
this.setListeners(this.getSpringFactoriesInstances(ApplicationListener.class));
this.mainApplicationClass = this.deduceMainApplicationClass();

```
#### WebApplicationType.deduceFromClasspath
1. REACTIVE: org.springframework.web.reactive.DispatcherHandler rg.springframework.web.servlet.DispatcherServlet! org.glassfish.jersey.servlet.ServletContainer!
2. NONE: !(javax.servlet.Servlet", "org.springframework.web.context.ConfigurableWebApplicationContext) ->hard coded
3. SERVLET: 
#### getSpringFactoriesInstances: 
** get class name and instantiate  factory classes specified in meta-info/spring.factories  for 'ApplicationContextInitializer' to be initializers,which defaultly are:
 org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer,\
 org.springframework.boot.context.ContextIdApplicationContextInitializer,\
 org.springframework.boot.context.config.DelegatingApplicationContextInitializer,\
 org.springframework.boot.web.context.ServerPortInfoApplicationContextInitializer
 **
#### setListeners: getSpringFactoriesInstances:
** same process as above, except this step is to retrive factory classes for 'ApplicationListener', and set to listeners. the factory classes specified in spring.factories are:
org.springframework.boot.ClearCachesApplicationListener,\
org.springframework.boot.builder.ParentContextCloserApplicationListener,\
org.springframework.boot.context.FileEncodingApplicationListener,\
org.springframework.boot.context.config.AnsiOutputApplicationListener,\
org.springframework.boot.context.config.ConfigFileApplicationListener,\
org.springframework.boot.context.config.DelegatingApplicationListener,\
org.springframework.boot.context.logging.ClasspathLoggingApplicationListener,\
org.springframework.boot.context.logging.LoggingApplicationListener,\
org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener
**

### Application::run
```
 StopWatch stopWatch = new StopWatch();
stopWatch.start();
ConfigurableApplicationContext context = null;
Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList();
this.configureHeadlessProperty();
SpringApplicationRunListeners listeners = this.getRunListeners(args);
listeners.starting();

Collection exceptionReporters;
try {
    ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
    ConfigurableEnvironment environment = this.prepareEnvironment(listeners, applicationArguments);
    this.configureIgnoreBeanInfo(environment);
    Banner printedBanner = this.printBanner(environment);
    context = this.createApplicationContext();
    exceptionReporters = this.getSpringFactoriesInstances(SpringBootExceptionReporter.class, new Class[]{ConfigurableApplicationContext.class}, context);
    this.prepareContext(context, environment, listeners, applicationArguments, printedBanner);
    this.refreshContext(context);
    this.afterRefresh(context, applicationArguments);
    stopWatch.stop();
    if (this.logStartupInfo) {
        (new StartupInfoLogger(this.mainApplicationClass)).logStarted(this.getApplicationLog(), stopWatch);
    }

    listeners.started(context);
    this.callRunners(context, applicationArguments);
} catch (Throwable var10) {
    this.handleRunFailure(context, var10, exceptionReporters, listeners);
    throw new IllegalStateException(var10);
}

try {
    listeners.running(context);
    return context;
} catch (Throwable var9) {
    this.handleRunFailure(context, var9, exceptionReporters, (SpringApplicationRunListeners)null);
    throw new IllegalStateException(var9);
}
```
 #### getRunListeners
 **
 instantiate SpringApplicationRunListeners, which holds a list field of type SpringApplicationRunListener. and the value of this list is also loaded from spring.factories. which are:
 org.springframework.boot.context.event.EventPublishingRunListener 
 **
 
 #### listeners.starting()
 **
 start all the SpringApplicationRunListener loaded into SpringApplicationRunListeners, which actually is only EventPublishingRunListener. the initialMulticaster is an instantiation of SimpleApplicationEventMulticaster
 **
 ```
this.initialMulticaster.multicastEvent(new ApplicationStartingEvent(this.application, this.args));

```
 

