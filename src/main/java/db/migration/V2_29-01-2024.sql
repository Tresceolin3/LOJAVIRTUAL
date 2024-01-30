create or replace function validaChavePessoa()
	returns trigger 
	language plpgsql
	as $$

	declare existe integer;

	begin
	   /*New = carrega dados de insert e update
	   OLD = carrega dados da linhas antigas antes de atualizar*/
	   existe = (select count(1) from pessoa_fisica where id  = NEW.pessoa_id);
	   if (existe <= 0 ) then
	     existe = (select count(1) from pessoa_juridica where id = NEW.pessoa_id);
	   if (existe <= 0 ) then
		raise exception 'Nao foi encontrado ID ou PK da pessoa para realizar a associacao';
	   end if;
	   end if;
	   RETURN NEW;
	end;
	$$

create trigger validaChaveVendacompraljvirt
before update
on vd_cp_loja_virt
for each row
execute procedure validaChavePessoa();

create trigger validaChaveVendacompraljvirt2
before insert
on vd_cp_loja_virt
for each row
execute procedure validaChavePessoa();
