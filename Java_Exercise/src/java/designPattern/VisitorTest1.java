package designPattern;

import java.util.ArrayList;
import java.util.List;

public class VisitorTest1 {
    public static void main(String[] args) {
        List<License> list = new ArrayList<>();
        list.add(new DownloadingLicense());
        list.add(new DownloadingLicense());
        list.add(new ViewingLicense());

        System.out.println(list.stream().filter(DownloadManager::ifDownloadable).count());
        System.out.println(list.stream().filter(DownloadManager::ifViewable).count());
    }
}

interface License {
    boolean accept(LicenseVisitor visitor);
}

class DownloadingLicense implements License {

    @Override
    public boolean accept(LicenseVisitor visitor) {
       return  visitor.visit(this);
    }
}

class ViewingLicense implements License {

    @Override
    public boolean accept(LicenseVisitor visitor) {
        return visitor.visit(this);
    }
}

interface LicenseVisitor {
    default boolean visit(DownloadingLicense license){
        return false;
    }

    default boolean visit(ViewingLicense license){
        return false;
    }
}

class DownloadManager {
    public static boolean ifDownloadable(License license) {
        return license.accept(new LicenseVisitor() {
            @Override
            public boolean visit(DownloadingLicense license) {
                return true;
            }
        });
    }

    public static boolean ifViewable(License license) {
        return license.accept(new LicenseVisitor() {
            @Override
            public boolean visit(ViewingLicense license) {
                return true;
            }
        });
    }
}