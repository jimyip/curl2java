package wiki.sogou;

import wiki.sogou.cli.Option;
import wiki.sogou.cli.Options;

public class Arguments {
    final static Options OPTIONS = new Options();

    final static Option AUTH = OPTIONS.add(Option.builder("u").longOpt("username").desc("credentials").hasArg(true).desc("user:password").build());
    final static Option CACERT = OPTIONS.add(Option.builder().longOpt("cacert").desc("CA certificate").hasArg(true).desc("CA_CERT").build());
    final static Option CERT = OPTIONS.add(Option.builder("E").longOpt("cert").desc("client certificate").hasArg(true).desc("CERT[:password]").build());
    final static Option CERT_TYPE = OPTIONS.add(Option.builder().longOpt("cert-type").desc("certificate type").hasArg(true).desc("PEM|P12|JKS|DER|ENG").build());
    final static Option COMPRESSED = OPTIONS.add(Option.builder().longOpt("compressed").desc("Request compressed response").hasArg(false).build());
    final static Option CONNECT_TIMEOUT = OPTIONS.add(Option.builder().longOpt("connect-timeout").desc("Maximum time allowed for connection").hasArg(true).argName("seconds").build());
    final static Option DATA = OPTIONS.add(Option.builder("d").longOpt("data").desc("Data").hasArg().argName("payload").build());
    final static Option DATA_BINARY = OPTIONS.add(Option.builder().longOpt("data-binary").desc("http post binary data").hasArg().argName("payload").build());
    final static Option DATA_URLENCODE = OPTIONS.add(Option.builder().longOpt("data-urlencode").desc("Data to URLEncode").hasArg().argName("payload").build());
    final static Option FOLLOW_REDIRECTS = OPTIONS.add(Option.builder("L").longOpt("location").desc("follow redirects").hasArg(false).build());
    final static Option FORM = OPTIONS.add(Option.builder("F").longOpt("form").desc("http multipart post data").hasArg(true).build());
    final static Option HEADER = OPTIONS.add(Option.builder("H").longOpt("header").desc("Header").hasArg().argName("headerValue").build());
    final static Option HTTP_METHOD = OPTIONS.add(Option.builder("X").longOpt("request").desc("Http Method").hasArg().argName("method").build());
    final static Option KEY = OPTIONS.add(Option.builder().longOpt("key").desc("key").hasArg(true).desc("KEY").build());
    final static Option KEY_TYPE = OPTIONS.add(Option.builder().longOpt("key-type").desc("key type").hasArg(true).desc("PEM|P12|JKS|DER|ENG").build());
    final static Option MAX_TIME = OPTIONS.add(Option.builder("m").longOpt("max-time").desc("Maximum time allowed for the transfer").hasArg(true).argName("seconds").build());
    final static Option NO_KEEPALIVE = OPTIONS.add(Option.builder().longOpt("no-keepalive").desc("Disable TCP keepalive on the connection").hasArg(false).build());
    final static Option NTLM = OPTIONS.add(Option.builder().longOpt("ntlm").desc("NTLM auth").hasArg(false).build());
    final static Option OUTPUT = OPTIONS.add(Option.builder("o").longOpt("output").desc("write to file").hasArg(true).argName("FILE").build());
    final static Option PROXY = OPTIONS.add(Option.builder("x").longOpt("proxy").desc("use the specified HTTP proxy").hasArg(true).argName("<[protocol://][user:password@]proxyhost[:port]>").build());
    final static Option PROXY_USER = OPTIONS.add(Option.builder("U").longOpt("proxy-user").desc("authentication for proxy").hasArg(true).argName("user[:password]").build());
    final static Option TLS_V1 = OPTIONS.add(Option.builder("1").longOpt("tlsv1").desc("use >= TLSv1 (SSL)").hasArg(false).build());
    final static Option TLS_V10 = OPTIONS.add(Option.builder().longOpt("tlsv1.0").desc("use TLSv1.0 (SSL)").hasArg(false).build());
    final static Option TLS_V11 = OPTIONS.add(Option.builder().longOpt("tlsv1.1").desc("use TLSv1.1 (SSL)").hasArg(false).build());
    final static Option TLS_V12 = OPTIONS.add(Option.builder().longOpt("tlsv1.2").desc("use TLSv1.2 (SSL)").hasArg(false).build());
    final static Option SSL_V2 = OPTIONS.add(Option.builder("2").longOpt("sslv2").desc("use SSLv2 (SSL)").hasArg(false).build());
    final static Option SSL_V3 = OPTIONS.add(Option.builder("3").longOpt("sslv3").desc("use SSLv3 (SSL)").hasArg(false).build());
    final static Option TRUST_INSECURE = OPTIONS.add(Option.builder("k").longOpt("insecure").desc("trust insecure").hasArg(false).build());
    final static Option USER_AGENT = OPTIONS.add(Option.builder("A").longOpt("user-agent").desc("user agent").hasArg(true).build());
    final static Option VERSION = OPTIONS.add(Option.builder("V").longOpt("version").desc("get the version of this library").hasArg(false).build());
    final static Option INTERCEPTOR = OPTIONS.add(Option.builder().longOpt("interceptor").desc("interceptor field or method (syntax is classname::fieldname). Must be a BiFunction<HttpRequest, Supplier< HttpResponse>, HttpResponse> or will be discarded").hasArg(true).build());
    final static Option LIMIT_RATE = OPTIONS.add(Option.builder().longOpt("limit-rate").desc("").hasArg(true).build());
    final static Option COOKIE = OPTIONS.add(Option.builder("b").longOpt("cookie").desc("").hasArg(true).build());
    final static Option COOKIE_JAR = OPTIONS.add(Option.builder("c").longOpt("cookie-jar").desc("").hasArg(true).build());
    final static Option REFERER = OPTIONS.add(Option.builder("e").longOpt("referer").desc("").hasArg(true).build());
    final static Option GET = OPTIONS.add(Option.builder("G").longOpt("get").desc("").hasArg(false).build());
    final static Option VERBOSE = OPTIONS.add(Option.builder("v").longOpt("verbose").desc("").hasArg(false).build());
    final static Option TRACE = OPTIONS.add(Option.builder().longOpt("trace").desc("").hasArg(true).build());
    final static Option REMOTE_NAME = OPTIONS.add(Option.builder("O").longOpt("remote-name").desc("").hasArg(false).build());
    final static Option SHOW_ERROR = OPTIONS.add(Option.builder("S").longOpt("show-error").desc("").hasArg(false).build());
    final static Option SILENT = OPTIONS.add(Option.builder("s").longOpt("silent").desc("").hasArg(false).build());
    final static Option INCLUDE = OPTIONS.add(Option.builder("i").longOpt("include").desc("").hasArg(false).build());
    final static Option HEAD = OPTIONS.add(Option.builder("I").longOpt("head").desc("").hasArg(false).build());

}
