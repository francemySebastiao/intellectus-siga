CREATE VIEW V_PROG_CURDOC AS
SELECT C.PLANO,C.ID CURSO_ID,AL.ANO_LECTIVO,L.CODIGO_DOCENTE FROM T_LECIONA L,T_CURSO C,T_ANO_LECTIVO AL
WHERE L.CODIGO_CURSO=C.ID AND
      L.CODIGO_ANO_LECTIVO=AL.ID
GROUP BY C.PLANO,C.ID,AL.ANO_LECTIVO,L.CODIGO_DOCENTE 