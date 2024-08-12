package com.xuzhongjian.code;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String sqlQuery = "WITH\n" +
                "    active_users AS (\n" +
                "        SELECT DISTINCT signin.user_id\n" +
                "        FROM signin\n" +
                "                 JOIN activity a ON signin.activity_id = a.id\n" +
                "        WHERE a.start_time BETWEEN '2024-06-10 00:00:00' AND '2024-06-16 23:59:59'\n" +
                "          AND signin.type IN (1, 3)\n" +
                "          AND signin.is_back = 0\n" +
                "    ),\n" +
                "    users_n_studio as (\n" +
                "        SELECT users.id, studio.name\n" +
                "        FROM active_users\n" +
                "                 JOIN users ON active_users.user_id = users.id\n" +
                "                 JOIN studio ON users.belongs_studio_id = studio.id\n" +
                "        where users.is_del = 0\n" +
                "        ORDER BY users.id),\n" +
                "    before_membership AS (\n" +
                "        SELECT DISTINCT user_id\n" +
                "        FROM membership\n" +
                "        WHERE create_time < '2024-06-10 00:00:00'\n" +
                "          AND user_id IN (SELECT user_id FROM active_users)\n" +
                "          and is_del = 0\n" +
                "    ),\n" +
                "    after_membership AS (\n" +
                "        SELECT DISTINCT user_id\n" +
                "        FROM membership\n" +
                "        WHERE create_time >= '2024-06-10 00:00:00'\n" +
                "          AND user_id IN (SELECT user_id FROM active_users)\n" +
                "          and is_del = 0\n" +
                "    ),\n" +
                "    renewed_membership AS (\n" +
                "        SELECT au.user_id                                                                      AS active_users_user_id,\n" +
                "               CASE WHEN (bm.user_id IS NOT NULL AND am.user_id IS NOT NULL) THEN 1 ELSE 0 END AS renewed\n" +
                "        FROM active_users au\n" +
                "                 LEFT JOIN before_membership bm ON au.user_id = bm.user_id\n" +
                "                 LEFT JOIN after_membership am ON au.user_id = am.user_id\n" +
                "    ),\n" +
                "    remains_snapshot_data AS (\n" +
                "        SELECT membership_snapshot.m_user_id, sum(membership_snapshot.m_remains) as all_remains\n" +
                "        FROM membership_snapshot\n" +
                "        WHERE membership_snapshot.snapshot_time = '2024-06-10 00:00:00'\n" +
                "          AND membership_snapshot.m_user_id in (select user_id from active_users)\n" +
                "        group by membership_snapshot.m_user_id\n" +
                "        order by membership_snapshot.m_user_id\n" +
                "    ),\n" +
                "    all_data as (\n" +
                "        select users_n_studio.id, users_n_studio.name, renewed_membership.renewed, remains_snapshot_data.all_remains\n" +
                "        from users_n_studio\n" +
                "                 left join renewed_membership on users_n_studio.id = renewed_membership.active_users_user_id\n" +
                "                 left join remains_snapshot_data on remains_snapshot_data.m_user_id = users_n_studio.id\n" +
                "    )\n" +
                " select *,\n" +
                "        CASE\n" +
                "            when all_remains <= 4.9 THEN '少于4节'\n" +
                "            when all_remains BETWEEN 5 AND 9.9 THEN '5到9节'\n" +
                "            when all_remains BETWEEN 10 AND 14.9 THEN '10到14节'\n" +
                "            when all_remains BETWEEN 15 AND 19.9 THEN '15到19节'\n" +
                "            when all_remains BETWEEN 20 AND 29.9 THEN '20到29节'\n" +
                "            when all_remains BETWEEN 30 AND 39.9 THEN '30到39节'\n" +
                "            when all_remains >= 40 THEN '40节以上'\n" +
                "            when all_remains is null THEN '新用户'\n" +
                "            END AS remains_type\n" +
                " from all_data\n" +
                " order by remains_type;";

        Set<String> tables = extractExternalTables(sqlQuery);
        System.out.println("Extracted tables: " + tables);
    }

    public static Set<String> extractExternalTables(String sqlQuery) {
        Set<String> allTables = new HashSet<>();
        Set<String> withTables = new HashSet<>();

        // Extract WITH table names
        String withRegex = "WITH\\s+([a-zA-Z0-9_]+)\\s+AS|\\),\\s*([a-zA-Z0-9_]+)\\s+AS";
        Pattern withPattern = Pattern.compile(withRegex);
        Matcher withMatcher = withPattern.matcher(sqlQuery);

        while (withMatcher.find()) {
            if (withMatcher.group(1) != null) {
                withTables.add(withMatcher.group(1));
            } else if (withMatcher.group(2) != null) {
                withTables.add(withMatcher.group(2));
            }
        }

        // Extract all table names
        String tableRegex = "(FROM|JOIN)\\s+([a-zA-Z0-9_]+)";
        Pattern tablePattern = Pattern.compile(tableRegex);
        Matcher tableMatcher = tablePattern.matcher(sqlQuery);

        while (tableMatcher.find()) {
            String tableName = tableMatcher.group(2);
            // Only add the table name if it's not in the WITH tables
            if (!withTables.contains(tableName)) {
                allTables.add(tableName);
            }
        }

        return allTables;
    }
}
