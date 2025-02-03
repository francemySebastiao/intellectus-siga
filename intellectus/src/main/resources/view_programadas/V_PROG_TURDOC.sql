USE [siga_intellectus]
GO

/****** Object:  View [dbo].[V_PROG_LECIONA]    Script Date: 28/04/2019 09:25:56 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[V_PROG_TURDOC] AS
SELECT  ROW_NUMBER() OVER(ORDER BY B.TURMA ASC) ID,B.* FROM
(SELECT TR.TURMA TURMA,TR.ID TURMA_ID,AL.ANO_LECTIVO,L.CODIGO_DOCENTE DOCENTE,COUNT(*) TOTAL_AULAS FROM T_LECIONA L,T_TURMA TR,T_ANO_LECTIVO AL
WHERE L.codigo_turma=TR.ID     AND
	  L.CODIGO_TURMA=TR.ID     AND
	  L.CODIGO_ANO_LECTIVO=AL.ID
GROUP BY TR.TURMA,TR.ID,L.CODIGO_DOCENTE,AL.ANO_LECTIVO 
) B

GO


