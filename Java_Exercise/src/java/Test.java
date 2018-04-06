import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.*;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Locale.*;

public class Test {
    public static void main(String[] args) {
        String json = "script:{\\u0022name\\u0022:\\u0022addNode\\u0022,\\u0022type\\u0022:\\u0022Context\\u0022,\\u0022args\\u0022:{\\u0022pyContextReference\\u0022:\\u0022Primary\\u0022,\\u0022pyIsPossibleRecipient\\u0022:\\u0022false\\u0022,\\u0022pyContextClass\\u0022:\\u0022DMSandbox\\u0022,\\u0022pyTargetProperty\\u0022:\\u0022\\u0022,\\u0022pyDataType\\u0022:\\u0022\\u0022,\\u0022pyContextName\\u0022:\\u0022Ethan\\u0022,\\u0022pyMOName\\u0022:\\u0022For each Ethan in Primary\\u0022}}";
        Pattern pattern = Pattern.compile(".*pyContextName\\\\u0022:\\\\u0022(.*)\\\\u0022,.*");
        Matcher matcher = pattern.matcher(json);


        System.out.println("@@@ " + matcher.find());
        System.out.println("@@@ " + matcher.groupCount());
        System.out.println("@@@ " + matcher.group(1));

//        System.out.print(matcher.group(1));
    }

}
