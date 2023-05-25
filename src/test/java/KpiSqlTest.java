import org.junit.Test;

import java.util.List;

/**
 * @author szy
 * @date 2023/3/9 10:05
 */
public class KpiSqlTest {
    @Test
    public void name() {
        List<String> kpiList = List.of("'GRE050305'","'GRE050306'","'GRE050307'","'GRE050308'","'GRE050315'","'GRE050316'","'GRE050317'","'GRE050318'","'GRE050325'","'GRE050326'","'GRE050327'","'GRE050328'");
        String citySql = generateKpiGcPoolCityDay(kpiList);
        String townSql = generateKpiGcPoolTownDay(kpiList);
        System.out.println("citySql = " + citySql);
        System.out.println("townSql = " + townSql);
    }

    private String generateKpiGcPoolCityDay(List<String> kpiList) {
        String sql = "set @StampTime = date_format(date_sub(now(), interval 1 day),'%%Y%%m%%d');\n" +
                "\n" +
                "delete from gd_jk_nhm_test.t_kpi_gc_pool_city_day\n" +
                "where fk_kpi_id in (\n" +
                "\tselect id as kpiId \n" +
                "\tfrom gd_jk_nhm_test.t_kpi\n" +
                "\twhere id in (%s)\n" +
                ") and `timestamp` = @StampTime;\n" +
                "insert into gd_jk_nhm_test.t_kpi_gc_pool_city_day(\n" +
                "\tid,\n" +
                "\tfk_kpi_id,\n" +
                "\tsrc_value,\n" +
                "\tcity,\n" +
                "\t`timestamp`,\n" +
                "\tcreated_by,\n" +
                "\tcreated_date,\n" +
                "\tlast_modified_by,\n" +
                "\tlast_modified_date\n" +
                ")\n" +
                "select replace(md5(uuid()), '-', '') as id,\n" +
                "\tkpi.kpiId as fk_kpi_id,\n" +
                "\trand() * 20 + 80 as src_value,\n" +
                "\tcity.id as city,\n" +
                "\t@StampTime as `timestamp`,\n" +
                "\t'sys' as created_by,\n" +
                "\tnow() as created_date,\n" +
                "\t'sys' as last_modified_by,\n" +
                "\tnow() as last_modified_date\n" +
                "from gd_jk_cor_test.t_sys_city city\n" +
                "left join (\n" +
                "\tselect id as kpiId \n" +
                "\tfrom gd_jk_nhm_test.t_kpi\n" +
                "\twhere id in (%s)\n" +
                ") kpi on 1=1\n" +
                "where city.id != 10;\n" +
                "select count(1) from gd_jk_nhm_test.t_kpi_gc_pool_city_day \n" +
                "where fk_kpi_id in (\n" +
                "\tselect id as kpiId \n" +
                "\tfrom gd_jk_nhm_test.t_kpi\n" +
                "\twhere id in (%s)\n" +
                ") and `timestamp` = @StampTime;";
        return String.format(sql, String.join(",", kpiList), String.join(",", kpiList), String.join(",", kpiList));
    }

    private String generateKpiGcPoolTownDay(List<String> kpiList) {
        String sql = "set @StampTime = date_format(date_sub(now(), interval 1 day),'%%Y%%m%%d');\n" +
                "\n" +
                "delete from gd_jk_nhm_test.t_kpi_gc_pool_town_day\n" +
                "where fk_kpi_id in (\n" +
                "\tselect id as kpiId \n" +
                "\tfrom gd_jk_nhm_test.t_kpi\n" +
                "\twhere id in (%s)\n" +
                ") and `timestamp` = @StampTime;\n" +
                "insert into gd_jk_nhm_test.t_kpi_gc_pool_town_day(\n" +
                "\tid,\n" +
                "\tfk_kpi_id,\n" +
                "\tsrc_value,\n" +
                "\tcity,\n" +
                "\ttown,\n" +
                "\t`timestamp`,\n" +
                "\tcreated_by,\n" +
                "\tcreated_date,\n" +
                "\tlast_modified_by,\n" +
                "\tlast_modified_date\n" +
                ")\n" +
                "select replace(md5(uuid()), '-', '') as id,\n" +
                "\tkpi.kpiId as fk_kpi_id,\n" +
                "\trand() * 20 + 80 as src_value,\n" +
                "\ttown.fk_sys_city_id as city,\n" +
                "\ttown.id as town,\n" +
                "\t@StampTime as `timestamp`,\n" +
                "\t'sys' as created_by,\n" +
                "\tnow() as created_date,\n" +
                "\t'sys' as last_modified_by,\n" +
                "\tnow() as last_modified_date\n" +
                "from gd_jk_cor_test.t_sys_town town\n" +
                "left join (\n" +
                "\tselect id as kpiId \n" +
                "\tfrom gd_jk_nhm_test.t_kpi\n" +
                "\twhere id in (%s)\n" +
                ") kpi on 1=1;\n" +
                "select count(1) from gd_jk_nhm_test.t_kpi_gc_pool_town_day \n" +
                "where fk_kpi_id in (\n" +
                "\tselect id as kpiId \n" +
                "\tfrom gd_jk_nhm_test.t_kpi\n" +
                "\twhere id in (%s)\n" +
                ") and `timestamp` = @StampTime;";
        return String.format(sql, String.join(",", kpiList), String.join(",", kpiList), String.join(",", kpiList));
    }
}



