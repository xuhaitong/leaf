<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 消息表Mapper文件 -->
<mapper namespace="com.crrc.imos.dao.system.PConMessageDao">

    <!--实体映射-->
    <resultMap id="pConMessageResultMap" type="PConMessage">
        <!--主键-->
        <id property="id" column="ID" />
        <!--消息类型 0:消息 1:通知-->
        <result property="type" column="TYPE" />
        <!--消息渠道 0:系统 1:现场呼叫-->
        <result property="channel" column="CHANNEL" />
        <!--消息等级 0:一般 1:重要 2:特等-->
        <result property="level" column="LEVEL" />
        <!--发送人-->
        <result property="sender" column="SENDER" />
        <!--发送时间-->
        <result property="sendTime" column="SEND_TIME" />
        <!--消息内容-->
        <result property="content" column="CONTENT" />
        <!--接收人-->
        <result property="receiver" column="RECEIVER" />
        <!--消息状态 0:未读 1:已读-->
        <result property="status" column="STATUS" />
        <!--设定编码-->
        <result property="dataCode" column="DATA_CODE" />
        <!--工厂ID从session获取当登录人工厂ID-->
        <result property="factoryId" column="FACTORY_ID" />
        <!--是否有效1无效  0有效-->
        <result property="isFlag" column="IS_FLAG" />
        <!--是否删除1已删除  0未删除-->
        <result property="delFlag" column="DEL_FLAG" />
        <!--创建人-->
        <result property="createBy" column="CREATE_BY" />
        <!--创建时间-->
        <result property="createTime" column="CREATE_TIME" />
        <!--修改人-->
        <result property="lastUpBy" column="LAST_UP_BY" />
        <!--修改时间-->
        <result property="lastUpTime" column="LAST_UP_TIME" />
        <!--消息类型名称-->
        <result property="typeName" column="TYPE_NAME" />
        <!--消息渠道名称-->
        <result property="channelName" column="CHANNEL_NAME" />
        <!--消息等级名称-->
        <result property="levelName" column="LEVEL_NAME" />
        <!--发送人名称-->
        <result property="senderName" column="SENDER_NAME" />
        <!--接收人名称-->
        <result property="receiverName" column="RECEIVER_NAME" />
        <!--消息状态名称-->
        <result property="statusName" column="STATUS_NAME" />
    </resultMap>

    <!-- =============== -->
    <!-- 一些公共变量设置 -->
    <!-- =============== -->
    <!-- mysql 查询总数 -->
    <sql id="count_Tail">
        select count(1) from
    </sql>

    <!-- 通用查询结果列-->
    <sql id="baseColumnList">
        <![CDATA[
        t.ID,
        t.TYPE,
        t.CHANNEL,
        t.LEVEL,
        t.SENDER,
        t.SEND_TIME,
        t.CONTENT,
        t.RECEIVER,
        t.STATUS,
        t.DATA_CODE,
        t.FACTORY_ID,
        t.IS_FLAG,
        t.DEL_FLAG,
        t.CREATE_BY,
        t.CREATE_TIME,
        t.LAST_UP_BY,
        t.LAST_UP_TIME
        ]]>
    </sql>

    <!-- 模糊字符匹配查询条件 -->
    <sql id="where_fragement">
        <if test="o.id != null and o.id != ''">
            AND t.ID LIKE LOWER(CONCAT("%", #{o.id},"%"))
        </if>
        <if test="o.type != null and o.type != ''">
            AND t.TYPE LIKE LOWER(CONCAT("%", #{o.type},"%"))
        </if>
        <if test="o.channel != null and o.channel != ''">
            AND t.CHANNEL LIKE LOWER(CONCAT("%", #{o.channel},"%"))
        </if>
        <if test="o.level != null and o.level != ''">
            AND t.LEVEL LIKE LOWER(CONCAT("%", #{o.level},"%"))
        </if>
        <if test="o.sender != null and o.sender != ''">
            AND t.SENDER LIKE LOWER(CONCAT("%", #{o.sender},"%"))
        </if>
        <if test="o.sendTime != null">
            AND t.SEND_TIME = #{o.sendTime}
        </if>
        <if test="o.content != null and o.content != ''">
            AND t.CONTENT LIKE LOWER(CONCAT("%", #{o.content},"%"))
        </if>
        <if test="o.receiver != null and o.receiver != ''">
            AND t.RECEIVER LIKE LOWER(CONCAT("%", #{o.receiver},"%"))
        </if>
        <if test="o.status != null and o.status != ''">
            AND t.STATUS LIKE LOWER(CONCAT("%", #{o.status},"%"))
        </if>
        <if test="o.dataCode != null and o.dataCode != ''">
            AND t.DATA_CODE LIKE LOWER(CONCAT("%", #{o.dataCode},"%"))
        </if>
        <if test="o.factoryId != null and o.factoryId != ''">
            AND t.FACTORY_ID LIKE LOWER(CONCAT("%", #{o.factoryId},"%"))
        </if>
        <if test="o.isFlag != null and o.isFlag != ''">
            AND t.IS_FLAG LIKE LOWER(CONCAT("%", #{o.isFlag},"%"))
        </if>
        <if test="o.delFlag != null and o.delFlag != ''">
            AND t.DEL_FLAG LIKE LOWER(CONCAT("%", #{o.delFlag},"%"))
        </if>
        <if test="o.createBy != null and o.createBy != ''">
            AND t.CREATE_BY LIKE LOWER(CONCAT("%", #{o.createBy},"%"))
        </if>
        <if test="o.createTime != null">
            AND t.CREATE_TIME = #{o.createTime}
        </if>
        <if test="o.lastUpBy != null and o.lastUpBy != ''">
            AND t.LAST_UP_BY LIKE LOWER(CONCAT("%", #{o.lastUpBy},"%"))
        </if>
        <if test="o.lastUpTime != null">
            AND t.LAST_UP_TIME = #{o.lastUpTime}
        </if>
        <!--<if test="o.multiQuery != null and o.multiQuery != ''">
			AND (
			<![CDATA[ INSTR(t1.CODE_KEY_VALUE, #{o.multiQuery}) > 0 ]]>
			OR <![CDATA[ INSTR(t3.CODE_KEY_VALUE, #{o.multiQuery}) > 0 ]]>
			OR <![CDATA[ INSTR(t.CONTENT, #{o.multiQuery}) > 0 ]]>
			OR <![CDATA[ INSTR(t4.USER_NAME, #{o.multiQuery}) > 0 ]]>
			OR <![CDATA[ INSTR(DATE_FORMAT(t.SEND_TIME, '%Y-%m-%d %H:%i:%s'), #{o.multiQuery}) > 0 ]]>
			)
		</if>
		<if test="o.dataQuery != null and o.dataQuery != ''">
			${o.dataQuery}
		</if>-->
    </sql>

    <!-- 全字符匹配查询 条件 -->
    <sql id="where_any">
        <if test="id != null and id != ''">
            AND t.ID = #{id}
        </if>
        <if test="type != null and type != ''">
            AND t.TYPE = #{type}
        </if>
        <if test="channel != null and channel != ''">
            AND t.CHANNEL = #{channel}
        </if>
        <if test="level != null and level != ''">
            AND t.LEVEL = #{level}
        </if>
        <if test="sender != null and sender != ''">
            AND t.SENDER = #{sender}
        </if>
        <if test="sendTime != null">
            AND t.SEND_TIME = #{sendTime}
        </if>
        <if test="content != null and content != ''">
            AND t.CONTENT = #{content}
        </if>
        <if test="receiver != null and receiver != ''">
            AND t.RECEIVER = #{receiver}
        </if>
        <if test="status != null and status != ''">
            AND t.STATUS = #{status}
        </if>
        <if test="dataCode != null and dataCode != ''">
            AND t.DATA_CODE = #{dataCode}
        </if>
        <if test="factoryId != null and factoryId != ''">
            AND t.FACTORY_ID = #{factoryId}
        </if>
        <if test="isFlag != null and isFlag != ''">
            AND t.IS_FLAG = #{isFlag}
        </if>
        <if test="delFlag != null and delFlag != ''">
            AND t.DEL_FLAG = #{delFlag}
        </if>
        <if test="createBy != null and createBy != ''">
            AND t.CREATE_BY = #{createBy}
        </if>
        <if test="createTime != null">
            AND t.CREATE_TIME = #{createTime}
        </if>
        <if test="lastUpBy != null and lastUpBy != ''">
            AND t.LAST_UP_BY = #{lastUpBy}
        </if>
        <if test="lastUpTime != null">
            AND t.LAST_UP_TIME = #{lastUpTime}
        </if>
        <if test="dataQuery != null and dataQuery != ''">
            ${dataQuery}
        </if>
    </sql>

    <!-- ========================= -->
    <!-- DAO方法中的基本增删改查方法 -->
    <!-- ========================= -->
    <!-- 添加 -->
    <insert id="insert" parameterType="PConMessage">
        INSERT INTO p_con_message (
        ID,
        TYPE,
        CHANNEL,
        LEVEL,
        SENDER,
        SEND_TIME,
        CONTENT,
        RECEIVER,
        STATUS,
        DATA_CODE,
        FACTORY_ID,
        IS_FLAG,
        DEL_FLAG,
        CREATE_BY,
        CREATE_TIME,
        LAST_UP_BY,
        LAST_UP_TIME
        ) VALUES (
        #{id},
        #{type},
        #{channel},
        #{level},
        #{sender},
        #{sendTime},
        #{content},
        #{receiver},
        #{status},
        #{dataCode},
        #{factoryId},
        #{isFlag},
        #{delFlag},
        #{createBy},
        now(),
        #{lastUpBy},
        #{lastUpTime}
        )
    </insert>

    <!-- 批量插入 -->
    <insert id="insertBatch" parameterType="java.util.List">
        <foreach collection="list" item="model" index="index" separator=";">
            INSERT INTO p_con_message (
            ID,
            TYPE,
            CHANNEL,
            LEVEL,
            SENDER,
            SEND_TIME,
            CONTENT,
            RECEIVER,
            STATUS,
            DATA_CODE,
            FACTORY_ID,
            IS_FLAG,
            DEL_FLAG,
            CREATE_BY,
            CREATE_TIME,
            LAST_UP_BY,
            LAST_UP_TIME
            ) VALUES (
            #{model.id},
            #{model.type},
            #{model.channel},
            #{model.level},
            #{model.sender},
            #{model.sendTime},
            #{model.content},
            #{model.receiver},
            #{model.status},
            #{model.dataCode},
            #{model.factoryId},
            #{model.isFlag},
            #{model.delFlag},
            #{model.createBy},
            now(),
            #{model.lastUpBy},
            #{model.lastUpTime}
            )
        </foreach>
    </insert>

    <!-- 根据id删除 -->
    <delete id="deleteById" parameterType="java.lang.String">
        UPDATE p_con_message SET DEL_FLAG = '1' WHERE ID = #{id}
    </delete>

    <!--删除：根据条件删除-->
    <delete id="delete" parameterType="PConMessage">
        UPDATE p_con_message t SET t.DEL_FLAG = '1', t.LAST_UP_TIME = now(), t.LAST_UP_BY = #{userID}
        WHERE 1 = 1
        <include refid="where_any"/>
    </delete>

    <!--删除：根据id批量删除-->
    <delete id="deleteBatchIds" parameterType="java.util.List">
        UPDATE p_con_message SET DEL_FLAG = '1', LAST_UP_TIME = now()
        WHERE 1 = 1
        AND ID IN
        <foreach item="item" collection="list" open="(" separator="," close=")">
            #{item}
        </foreach>
    </delete>

    <!--删除：根据id批量删除-->
    <delete id="deleteMany">
        UPDATE p_con_message SET DEL_FLAG = '1', LAST_UP_TIME = now(), LAST_UP_BY = #{userId}
        WHERE 1 = 1
        AND ID IN
        <foreach item="item" collection="list" open="(" separator="," close=")">
            #{item}
        </foreach>
    </delete>

    <!-- 修 改-->
    <update id="update" parameterType="PConMessage">
        UPDATE p_con_message
        <set>
            LAST_UP_TIME = now(),
            <if test="type != null">
                TYPE = #{type},
            </if>
            <if test="channel != null">
                CHANNEL = #{channel},
            </if>
            <if test="level != null">
                LEVEL = #{level},
            </if>
            <if test="sender != null">
                SENDER = #{sender},
            </if>
            <if test="sendTime != null">
                SEND_TIME = #{sendTime},
            </if>
            <if test="content != null">
                CONTENT = #{content},
            </if>
            <if test="receiver != null">
                RECEIVER = #{receiver},
            </if>
            <if test="status != null">
                STATUS = #{status},
            </if>
            <if test="dataCode != null">
                DATA_CODE = #{dataCode},
            </if>
            <if test="factoryId != null">
                FACTORY_ID = #{factoryId},
            </if>
            <if test="isFlag != null">
                IS_FLAG = #{isFlag},
            </if>
            <if test="delFlag != null">
                DEL_FLAG = #{delFlag},
            </if>
            <if test="createBy != null">
                CREATE_BY = #{createBy},
            </if>
            <if test="createTime != null">
                CREATE_TIME = #{createTime},
            </if>
            <if test="lastUpBy != null">
                LAST_UP_BY = #{lastUpBy},
            </if>
        </set>
        WHERE ID = #{id}
    </update>

    <!-- 批量更新 -->
    <update id="updateBatchIds" parameterType="java.util.List">
        <foreach collection="list" item="model" separator=";">
            UPDATE p_con_message
            <set>
                LAST_UP_TIME = now(),
                <if test="model.type != null">
                    TYPE = #{model.type},
                </if>
                <if test="model.channel != null">
                    CHANNEL = #{model.channel},
                </if>
                <if test="model.level != null">
                    LEVEL = #{model.level},
                </if>
                <if test="model.sender != null">
                    SENDER = #{model.sender},
                </if>
                <if test="model.sendTime != null">
                    SEND_TIME = #{model.sendTime},
                </if>
                <if test="model.content != null">
                    CONTENT = #{model.content},
                </if>
                <if test="model.receiver != null">
                    RECEIVER = #{model.receiver},
                </if>
                <if test="model.status != null">
                    STATUS = #{model.status},
                </if>
                <if test="model.dataCode != null">
                    DATA_CODE = #{model.dataCode},
                </if>
                <if test="model.factoryId != null">
                    FACTORY_ID = #{model.factoryId},
                </if>
                <if test="model.isFlag != null">
                    IS_FLAG = #{model.isFlag},
                </if>
                <if test="model.delFlag != null">
                    DEL_FLAG = #{model.delFlag},
                </if>
                <if test="model.createBy != null">
                    CREATE_BY = #{model.createBy},
                </if>
                <if test="model.createTime != null">
                    CREATE_TIME = #{model.createTime},
                </if>
                <if test="model.lastUpBy != null">
                    LAST_UP_BY = #{model.lastUpBy},
                </if>
            </set>
            WHERE ID = #{model.id}
        </foreach>
    </update>

    <!-- 根据id查询 -->
    <select id="selectById" resultMap="pConMessageResultMap" parameterType="java.lang.String">
        SELECT
        t1.CODE_KEY_VALUE AS TYPE_NAME,
        t2.CODE_KEY_VALUE AS CHANNEL_NAME,
        t3.CODE_KEY_VALUE AS LEVEL_NAME,
        t4.USER_NAME as SENDER_NAME,
        t5.USER_NAME as RECEIVER_NAME,
        t6.CODE_KEY_VALUE AS STATUS_NAME,
        <include refid="baseColumnList" />
        FROM p_con_message t
        LEFT JOIN p_con_dic_slavecode t1 ON t.TYPE = t1.CODE_KEY AND t1.CODE_DICS = 'MESSAGE_TYPE'
        LEFT JOIN p_con_dic_slavecode t2 ON t.CHANNEL = t2.CODE_KEY AND t2.CODE_DICS = 'MESSAGE_CHANNEL'
        LEFT JOIN p_con_dic_slavecode t3 ON t.LEVEL = t3.CODE_KEY AND t3.CODE_DICS = 'MESSAGE_LEVEL'
        LEFT JOIN p_con_user t4 on t.SENDER = t4.USER_ID
        LEFT JOIN p_con_user t5 on t.RECEIVER = t5.USER_ID
        LEFT JOIN p_con_dic_slavecode t6 ON t.STATUS = t6.CODE_KEY AND t6.CODE_DICS = 'MESSAGE_STATUS'
        WHERE t.DEL_FLAG = '0' AND t.ID = #{id}
    </select>

    <!-- 根据多个id查询 -->
    <select id="selectBatchIds" resultMap="pConMessageResultMap">
        SELECT
        <include refid="baseColumnList" />
        FROM p_con_message t
        WHERE t.DEL_FLAG = '0'
        AND t.ID IN
        <foreach item="item" collection="list" open="(" separator="," close=")">
            #{item}
        </foreach>
    </select>

    <!-- 根据条件查询一条数据 -->
    <select id="selectOne" resultMap="pConMessageResultMap" parameterType="PConMessage">
        SELECT
        t1.CODE_KEY_VALUE AS TYPE_NAME,
        t2.CODE_KEY_VALUE AS CHANNEL_NAME,
        t3.CODE_KEY_VALUE AS LEVEL_NAME,
        t4.USER_NAME as SENDER_NAME,
        t5.USER_NAME as RECEIVER_NAME,
        t6.CODE_KEY_VALUE AS STATUS_NAME,
        <include refid="baseColumnList" />
        FROM p_con_message t
        LEFT JOIN p_con_dic_slavecode t1 ON t.TYPE = t1.CODE_KEY AND t1.CODE_DICS = 'MESSAGE_TYPE'
        LEFT JOIN p_con_dic_slavecode t2 ON t.CHANNEL = t2.CODE_KEY AND t2.CODE_DICS = 'MESSAGE_CHANNEL'
        LEFT JOIN p_con_dic_slavecode t3 ON t.LEVEL = t3.CODE_KEY AND t3.CODE_DICS = 'MESSAGE_LEVEL'
        LEFT JOIN p_con_user t4 on t.SENDER = t4.USER_ID
        LEFT JOIN p_con_user t5 on t.RECEIVER = t5.USER_ID
        LEFT JOIN p_con_dic_slavecode t6 ON t.STATUS = t6.CODE_KEY AND t6.CODE_DICS = 'MESSAGE_STATUS'
        WHERE 1 = 1
        <include refid="where_any"/>
        LIMIT 1
    </select>

    <!--查询数量-->
    <select id="selectCount" resultType="long">
        <include refid="count_Tail" />
        p_con_message t
        LEFT JOIN p_con_dic_slavecode t1 ON t.TYPE = t1.CODE_KEY AND t1.CODE_DICS = 'MESSAGE_TYPE'
        LEFT JOIN p_con_dic_slavecode t3 ON t.LEVEL = t3.CODE_KEY AND t3.CODE_DICS = 'MESSAGE_LEVEL'
        LEFT JOIN p_con_user t4 on t.SENDER = t4.USER_ID
        WHERE t.DEL_FLAG = '0'
        <include refid="where_fragement" />
    </select>

    <!-- 任意字段全匹配查询 -->
    <select id="selectList" parameterType="PConMessage" resultMap="pConMessageResultMap">
        SELECT
        t1.CODE_KEY_VALUE AS TYPE_NAME,
        t2.CODE_KEY_VALUE AS CHANNEL_NAME,
        t3.CODE_KEY_VALUE AS LEVEL_NAME,
        t4.USER_NAME as SENDER_NAME,
        t5.USER_NAME as RECEIVER_NAME,
        t6.CODE_KEY_VALUE AS STATUS_NAME,
        <include refid="baseColumnList"/>
        FROM p_con_message t
        LEFT JOIN p_con_dic_slavecode t1 ON t.TYPE = t1.CODE_KEY AND t1.CODE_DICS = 'MESSAGE_TYPE'
        LEFT JOIN p_con_dic_slavecode t2 ON t.CHANNEL = t2.CODE_KEY AND t2.CODE_DICS = 'MESSAGE_CHANNEL'
        LEFT JOIN p_con_dic_slavecode t3 ON t.LEVEL = t3.CODE_KEY AND t3.CODE_DICS = 'MESSAGE_LEVEL'
        LEFT JOIN p_con_user t4 on t.SENDER = t4.USER_ID
        LEFT JOIN p_con_user t5 on t.RECEIVER = t5.USER_ID
        LEFT JOIN p_con_dic_slavecode t6 ON t.STATUS = t6.CODE_KEY AND t6.CODE_DICS = 'MESSAGE_STATUS'
        WHERE t.DEL_FLAG = '0'
        <include refid="where_any"/>
        ORDER BY t.STATUS, t.SEND_TIME DESC
    </select>

    <!-- 分页查询 -->
    <select id="queryPage" parameterType="PConMessage" resultMap="pConMessageResultMap">
        SELECT
        t1.CODE_KEY_VALUE AS TYPE_NAME,
        t2.CODE_KEY_VALUE AS CHANNEL_NAME,
        t3.CODE_KEY_VALUE AS LEVEL_NAME,
        t4.USER_NAME as SENDER_NAME,
        t5.USER_NAME as RECEIVER_NAME,
        t6.CODE_KEY_VALUE AS STATUS_NAME,
        <include refid="baseColumnList"/>
        FROM p_con_message t
        LEFT JOIN p_con_dic_slavecode t1 ON t.TYPE = t1.CODE_KEY AND t1.CODE_DICS = 'MESSAGE_TYPE' and t1.del_flag='0'
        LEFT JOIN p_con_dic_slavecode t2 ON t.CHANNEL = t2.CODE_KEY AND t2.CODE_DICS = 'MESSAGE_CHANNEL' and t2.del_flag='0'
        LEFT JOIN p_con_dic_slavecode t3 ON t.LEVEL = t3.CODE_KEY AND t3.CODE_DICS = 'MESSAGE_LEVEL' and t3.del_flag='0'
        LEFT JOIN p_con_user t4 on t.SENDER = t4.USER_ID and t4.del_flag='0'
        LEFT JOIN p_con_user t5 on t.RECEIVER = t5.USER_ID and t5.del_flag='0'
        LEFT JOIN p_con_dic_slavecode t6 ON t.STATUS = t6.CODE_KEY AND t6.CODE_DICS = 'MESSAGE_STATUS' and t6.del_flag='0'
        WHERE t.DEL_FLAG = '0'
        <include refid="where_fragement"/>
        <choose>
            <when test="pager.orderBy != '' and pager.orderBy != null">ORDER BY ${pager.orderBy}</when>
        </choose>
    </select>

</mapper>
