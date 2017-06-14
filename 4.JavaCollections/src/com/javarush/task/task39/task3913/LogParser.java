package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery {
    private Path logDir;

    public LogParser(Path logDir) {
        this.logDir = logDir;
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> uniqueIPs = new HashSet<>();

        for (String line : getLinesList()) {
            String[] parts = line.split("\\t");

            addEntity(after, before, uniqueIPs, parts, 0);
        }
        return uniqueIPs;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> IPsForUser = new HashSet<>();

        for (String line : getLinesList()) {
            String[] parts = line.split("\\t");

            if (user.equals(parts[1])) {
                addEntity(after, before, IPsForUser, parts, 0);
            }
        }
        return IPsForUser;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> IPsForEvent = new HashSet<>();

        for (String line : getLinesList()) {
            String[] parts = line.split("\\t");

            if (event.toString().equals(parts[3].split(" ")[0])) {
                addEntity(after, before, IPsForEvent, parts, 0);
            }
        }
        return IPsForEvent;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> IPsForEvent = new HashSet<>();

        for (String line : getLinesList()) {
            String[] parts = line.split("\\t");

            if (status.toString().equals(parts[4])) {
                addEntity(after, before, IPsForEvent, parts, 0);
            }
        }
        return IPsForEvent;
    }

    private List<String> getLinesList() {
        String[] files = logDir.toFile().list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".log");
            }
        });

        List<String> lines = new ArrayList<>();
        for (String file : files) {
            try {
                lines.addAll(Files.readAllLines(Paths.get(logDir + File.separator + file), Charset.defaultCharset()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }

    private void addEntity(Date after, Date before, Set<String> enteties, String[] parts, int part) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
        long partDate = 0;
        try {
            partDate = dateFormat.parse(parts[2]).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (after == null && before == null) {
            enteties.add(parts[part]);
        } else if (after == null) {
            if (partDate <= before.getTime()) {
                enteties.add(parts[part]);
            }
        } else if (before == null) {
            if (partDate >= after.getTime()) {
                enteties.add(parts[part]);
            }
        } else {
            if (partDate >= after.getTime() && partDate <= before.getTime()) {
                enteties.add(parts[part]);
            }
        }
    }


    @Override
    public Set<String> getAllUsers() {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (!users.contains(record.getUser())) {
                users.add(record.getUser());
            }
        }
        return users;
    }
    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (isDateInside(after, before, record.getDate()) && !users.contains(record.getUser())) {
                users.add(record.getUser());
            }
        }
        return users.size();
    }
    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (isDateInside(after, before, record.getDate())) {
                if (record.getUser().equals(user)) set.add(record.getEvent());
            }
        }
        return set.size();
    }
    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (isDateInside(after, before, record.getDate()) && record.getIp().equals(ip)) {
                users.add(record.getUser());
            }
        }
        return users;
    }
    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (isDateInside(after, before, record.getDate()) && record.getEvent().equals(Event.LOGIN)) {
                users.add(record.getUser());
            }
        }
        return users;
    }
    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (isDateInside(after, before, record.getDate()) && record.getEvent().equals(Event.DOWNLOAD_PLUGIN)) {
                users.add(record.getUser());
            }
        }
        return users;
    }
    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (isDateInside(after, before, record.getDate()) && record.getEvent().equals(Event.WRITE_MESSAGE)) {
                users.add(record.getUser());
            }
        }
        return users;
    }
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (isDateInside(after, before, record.getDate()) && record.getEvent().equals(Event.SOLVE_TASK)) {
                users.add(record.getUser());
            }
        }
        return users;
    }
    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (isDateInside(after, before, record.getDate())
                    && record.getEvent().equals(Event.SOLVE_TASK)
                    && record.getTaskNumber() != null
                    && !record.getTaskNumber().isEmpty()
                    && Integer.parseInt(record.getTaskNumber()) == task) {
                users.add(record.getUser());
            }
        }
        return users;
    }
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (isDateInside(after, before, record.getDate()) && record.getEvent().equals(Event.DONE_TASK)) {
                users.add(record.getUser());
            }
        }
        return users;
    }
    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (isDateInside(after, before, record.getDate())
                    && record.getEvent().equals(Event.DONE_TASK)
                    && record.getTaskNumber() != null
                    && !record.getTaskNumber().isEmpty()
                    && Integer.parseInt(record.getTaskNumber()) == task) {
                users.add(record.getUser());
            }
        }
        return users;
    }

    private boolean isFieldMatch(String field, String value, LogRecord record) throws ParseException {
        boolean criteria = false;
        if (field == null) return true;
        if (value == null) return false;
        switch (field) {
            case "ip": {
                criteria = record.getIp().equals(value);
                break;
            }
            case "user": {
                criteria = record.getUser().equals(value);
                break;
            }
            case "date": {
                criteria = record.getDate().equals(new SimpleDateFormat("d.M.yyyy H:m:s").parse(value));
                break;
            }
            case "event": {
                criteria = record.getEvent().equals(Event.valueOf(value));
                break;
            }
            case "status": {
                criteria = record.getStatus().equals(Status.valueOf(value));
                break;
            }
        }
        return criteria;
    }
    private Set<String> getAllIps(String field, String value, Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    users.add(record.getIp());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return users;
    }
    private Set<Date> getAllDates(String field, String value, Date after, Date before) {
        Set<Date> dates = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    dates.add(record.date);
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return dates;
    }
    private Set<Status> getAllStatuses(String field, String value, Date after, Date before) {
        Set<Status> set = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    set.add(record.getStatus());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return set;
    }
    private Set<Event> getAllEvents(String field, String value, Date after, Date before) {
        Set<Event> set = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    set.add(record.getEvent());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return set;
    }
    private Set<String> getAllUsers(String field, String value, Date after, Date before) {
        Set<String> users = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            try {
                if (isDateInside(after, before, record.getDate()) && isFieldMatch(field, value, record)) {
                    users.add(record.getUser());
                }
            } catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return users;
    }
    private Set<String> getIpSet(Object recordField, Date after, Date before) {
        Set<String> ipSet = new HashSet<>();
        for (LogRecord record : getParsedRecords(logDir)) {
            if (isDateInside(after, before, record.getDate()) && isFieldMatch(recordField, record)) {
                ipSet.add(record.getIp());
            }
        }
        return ipSet;
    }
    private List<LogRecord> getParsedRecords(Path logDir) {
        List<LogRecord> recordList = new ArrayList<>();
        try {
            for (File file : logDir.toFile().listFiles()) {
                if (file.isFile() && file.getName().toLowerCase().endsWith(".log"))
                    for (String record : Files.readAllLines(file.toPath())) {
                        recordList.add(new LogRecord(record));
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recordList;
    }
    private boolean isFieldMatch(Object recordField, LogRecord record) {
        boolean criteria = false;
        if (recordField == null)
            return true;
        if (recordField instanceof String)
            criteria = record.getUser().equals(recordField);
        else if (recordField instanceof Event)
            criteria = record.getEvent().equals(recordField);
        else if (recordField instanceof Status)
            criteria = record.getStatus().equals(recordField);
        return criteria;
    }
    private boolean isDateInside(Date after, Date before, Date currentDate) {
        if (after != null) {
            if (currentDate.getTime() < after.getTime())
                return false;
        }
        if (before != null) {
            if (currentDate.getTime() > before.getTime())
                return false;
        }
        return true;
    }
    private class LogRecord {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private String taskNumber;
        private Status status;
        public LogRecord(String ip, String user, Date date, Event event, Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.status = status;
        }
        public LogRecord(String record) {
            String[] strings = record.split("\t");
            this.ip = strings[0].trim();
            this.user = strings[1];
            SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy H:m:s");
            try {
                date = dateFormat.parse(strings[2]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String eventAndParameter[] = strings[3].split(" ");
            event = Event.valueOf(eventAndParameter[0]);
            if (eventAndParameter.length > 1) taskNumber = eventAndParameter[1];
            status = Status.valueOf(strings[4]);
        }
        public String getIp() {
            return ip;
        }
        public String getUser() {
            return user;
        }
        public Date getDate() {
            return date;
        }
        public Event getEvent() {
            return event;
        }
        public String getTaskNumber() {
            return taskNumber;
        }
        public Status getStatus() {
            return status;
        }
    }
}